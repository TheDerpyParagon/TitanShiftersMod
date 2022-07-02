package com.derpyzombie.titanshiftersmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.derpyzombie.titanshiftersmod.capabilities.ITitanShifters;
import com.derpyzombie.titanshiftersmod.capabilities.TitanShifters;
import com.derpyzombie.titanshiftersmod.capabilities.TitanShiftersProvider;
import com.derpyzombie.titanshiftersmod.capabilities.TitanShiftersStorage;
import com.derpyzombie.titanshiftersmod.common.events.EventHandler;
import com.derpyzombie.titanshiftersmod.common.events.PlayerSizeControl;
import com.derpyzombie.titanshiftersmod.common.events.TitanTransformation;
import com.derpyzombie.titanshiftersmod.common.events.titanAbilities.TitanAblilities;
import com.derpyzombie.titanshiftersmod.common.events.titanAbilities.TitanTransformationControl;
import com.derpyzombie.titanshiftersmod.common.items.init.ItemInit;
import com.derpyzombie.titanshiftersmod.common.reach.EntityReachExtender;
import com.derpyzombie.titanshiftersmod.core.network.TitanShiftersNetwork;
import com.derpyzombie.titanshiftersmod.mobs.MobInit;
import com.derpyzombie.titanshiftersmod.mobs.MobRegistry;
import com.derpyzombie.titanshiftersmod.util.SoundEvents;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("titanshiftersmod")
@Mod.EventBusSubscriber(modid = TitanShiftersMod.MOD_ID, bus = Bus.FORGE)
public class TitanShiftersMod
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "titanshiftersmod";
    public static final String MOD_VERSION = "0.2";
    
    public static final ResourceLocation resourceLocation = new ResourceLocation(MOD_ID, "puretitan");
    
    public static final Direction direction = Direction.UP;
    
	public TitanShiftersMod() {
    	IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    	
    	ItemInit.ITEMS.register(bus);
    	MobInit.ENTITIES.register(bus);
    	MinecraftForge.EVENT_BUS.register(new PlayerSizeControl());
    	MinecraftForge.EVENT_BUS.register(this);
    	MinecraftForge.EVENT_BUS.register(new EventHandler());
    	MinecraftForge.EVENT_BUS.register(new EntityReachExtender());
    	MinecraftForge.EVENT_BUS.register(new TitanTransformation());
    	MinecraftForge.EVENT_BUS.register(new MobRegistry());
    	MinecraftForge.EVENT_BUS.register(new TitanTransformationControl());
    	bus.addListener(TitanShiftersMod::onCommonSetup);
    	bus.addListener(TitanShiftersMod::registerEntities);
    	bus.addListener(this::commonSetup);
    	
    	SoundEvents.register(bus);
    	
    	MinecraftForge.EVENT_BUS.register(new TitanAblilities());
    }
    
    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
    	CapabilityManager.INSTANCE.register(ITitanShifters.class, new TitanShiftersStorage(), TitanShifters::new);
    	LOGGER.info("Capabilities registered");
    }
    
    @SubscribeEvent
    public void commonSetup(final FMLCommonSetupEvent event) {
    	TitanShiftersNetwork.init();
    }
    
     @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
    	if (event.getObject() instanceof PlayerEntity) {
    		event.addCapability(resourceLocation, new TitanShiftersProvider());
    	}
    }
     
     @SubscribeEvent
    public static void registerEntities(FMLClientSetupEvent event) {
   	  MobRegistry.registerEntities();
    }
 }

