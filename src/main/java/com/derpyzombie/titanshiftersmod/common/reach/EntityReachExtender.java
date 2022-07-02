package com.derpyzombie.titanshiftersmod.common.reach;

import java.util.Optional;

import com.derpyzombie.titanshiftersmod.TitanShiftersMod;
import com.derpyzombie.titanshiftersmod.capabilities.ITitanShifters;
import com.derpyzombie.titanshiftersmod.capabilities.TitanShifters;
import com.derpyzombie.titanshiftersmod.capabilities.TitanShiftersProvider;
import com.derpyzombie.titanshiftersmod.core.network.TitanShiftersNetwork;
import com.derpyzombie.titanshiftersmod.core.network.message.InputMessage;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickEmpty;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = TitanShiftersMod.MOD_ID, bus = Bus.FORGE)
public class EntityReachExtender {
	
	private static double range;
	
	public static void setEntityRange(double r) {
		range = r * 80;
	}
	
	public static double getEntityRange() {
		return range;
	}
	
	public static EntityRayTraceResult getPlayerPOVHitResult(PlayerEntity player) {
		  float playerRotX = player.xRot;
		  float playerRotY = player.yRot;
		  Vector3d startPos = player.getEyePosition(1);
		  float f2 = (float) Math.cos(-playerRotY * ((float)Math.PI / 180F) - (float)Math.PI);
		  float f3 = (float) Math.sin(-playerRotY * ((float)Math.PI / 180F) - (float)Math.PI);
		  float f4 = (float) -Math.cos(-playerRotX * ((float)Math.PI / 180F));
		  float additionY = (float) Math.sin(-playerRotX * ((float)Math.PI / 180F));
		  float additionX = f3 * f4;
		  float additionZ = f2 * f4;
		  double d0 = range;
		  Vector3d endVec = startPos.add((double)additionX * d0, (double)additionY * d0, (double)additionZ * d0);
		  AxisAlignedBB startEndBox = new AxisAlignedBB(startPos, endVec);
		  Entity entity = null;
		  for(Entity entity1 : player.level.getEntities(player, startEndBox, (val) -> true)) {
			AxisAlignedBB aabb = entity1.getBoundingBox().inflate(entity1.getPickRadius());
			Optional<Vector3d> optional = aabb.clip(startPos, endVec);
		    if (aabb.contains(startPos)) {
		      if (d0 >= 0.0D) {
		        entity = entity1;
		        startPos = optional.orElse(startPos);
		        d0 = 0.0D;
		      }
		    } else if (optional.isPresent()) {
		      Vector3d vec31 = optional.get();
		      double d1 = startPos.distanceToSqr(vec31);
		      if (d1 < d0 || d0 == 0.0D) {
		        if (entity1.getRootVehicle() == player.getRootVehicle() && !entity1.canRiderInteract()) {
		          if (d0 == 0.0D) {
		            entity = entity1;
		            startPos = vec31;
		          }
		        } else {
		          entity = entity1;
		          startPos = vec31;
		          d0 = d1;
		        }
		      }
		    }
		  }

		  return (entity == null) ? null:new EntityRayTraceResult(entity);

	}
	
	@SubscribeEvent
	public void entityReach(LeftClickEmpty event) {
		
		LazyOptional<ITitanShifters> titan = event.getPlayer().getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, TitanShiftersMod.direction);
		ITitanShifters titanShifter = titan.orElse(new TitanShifters());
		
		EntityRayTraceResult t = getPlayerPOVHitResult(event.getPlayer());
		if(titanShifter.getPureTitan()) {
			if(t != null) {
				if(t.getEntity() != null) {
					TitanShiftersNetwork.CHANNEL.sendToServer(new InputMessage(t.getEntity().getId()));
				}
			}
		}
	}
}
