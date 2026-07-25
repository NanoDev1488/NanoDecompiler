// исходный (обфусцированный) внутренний класс: dev.angelvisuals.AngelVisuals
package dev.angelvisuals;

import dev.angelvisuals.a.ClassA145;
import dev.angelvisuals.a.ClassA32;
import dev.angelvisuals.a.ClassA42;
import dev.angelvisuals.a.ClassA52;
import dev.angelvisuals.a.ad;
import dev.angelvisuals.a.af;
import dev.angelvisuals.a.bJ;
import dev.angelvisuals.a.bT;
import dev.angelvisuals.a.bj;
import dev.angelvisuals.a.bs;
import dev.angelvisuals.a.by;
import dev.angelvisuals.a.cM;
import dev.angelvisuals.a.ch;
import dev.angelvisuals.a.ck;
import dev.angelvisuals.a.cq;
import java.io.File;
import lombok.Generated;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.class_2960;
import ru.nexusguard.protection.annotations.Native;

public enum AngelVisuals implements ClientModInitializer {

    INSTANCE;

    // ---- поля ----
  public static final String NAME = "AngelVisuals";
  public static final String VER = "";
  public static final String TYPE = "DEV";
  private static final String MOD_ID;
  public static File DIRECTORY;
  private ClassA42 moduleManager;
  private ch themeManager;
  private af menuScreen;
  private ClassA52 scriptManager;
  private ClassA145 serverHandler;
  private bs friendManager;
  private by macroManager;
  private ClassA32 staffManager;
  private bJ waypointManager;
  private ad notifyManager;
  private bT commandManager;
  private cq configManager;
  private cM rctRepository;
  private ck discordManager;
  private bj toggleNotify;
  private boolean initialized;

    static {
        MOD_ID = "AngelVisuals".toLowerCase();
    }

  private AngelVisuals() { // было: <init>
        initialized = false;
    }

  public void onInitializeClient() {
        try {
            init();
        } catch (Exception var1) {
            throw var1;
        }
    }

    @Native
  public void init() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #52 // dev.angelvisuals.AngelVisuals.initialized:Z
        //      4: ifeq  8 (offset +4)
        //      7: return
        //      8: aload_0
        //      9: iconst_1
        //     10: putfield  #52 // dev.angelvisuals.AngelVisuals.initialized:Z
        //     13: new  #28 // java.io.File
        //     16: dup
        //     17: invokestatic  #109 // net.minecraft.class_310.method_1551:()Lnet/minecraft/class_310;
        //     20: getfield  #64 // net.minecraft.class_310.field_1697:Ljava/io/File;
        //     23: ldc  #2 // 'AngelVisuals'
        //     25: invokespecial  #97 // java.io.File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //     28: putstatic  #45 // dev.angelvisuals.AngelVisuals.DIRECTORY:Ljava/io/File;
        //     31: getstatic  #45 // dev.angelvisuals.AngelVisuals.DIRECTORY:Ljava/io/File;
        //     34: invokevirtual  #98 // java.io.File.exists:()Z
        //     37: ifne  47 (offset +10)
        //     40: getstatic  #45 // dev.angelvisuals.AngelVisuals.DIRECTORY:Ljava/io/File;
        //     43: invokevirtual  #99 // java.io.File.mkdirs:()Z
        //     46: pop
        //     47: invokestatic  #103 // java.lang.Runtime.getRuntime:()Ljava/lang/Runtime;
        //     50: new  #34 // java.lang.Thread
        //     53: dup
        //     54: invokedynamic  #112 // invokedynamic run:()Ljava/lang/Runnable;
        //     59: invokespecial  #106 // java.lang.Thread.<init>:(Ljava/lang/Runnable;)V
        //     62: invokevirtual  #102 // java.lang.Runtime.addShutdownHook:(Ljava/lang/Thread;)V
        //     65: aload_0
        //     66: new  #18 // dev.angelvisuals.a.bs
        //     69: dup
        //     70: invokespecial  #83 // dev.angelvisuals.a.bs.<init>:()V
        //     73: putfield  #51 // dev.angelvisuals.AngelVisuals.friendManager:Ldev/angelvisuals/a/bs;
        //     76: aload_0
        //     77: new  #20 // dev.angelvisuals.a.by
        //     80: dup
        //     81: invokespecial  #86 // dev.angelvisuals.a.by.<init>:()V
        //     84: putfield  #53 // dev.angelvisuals.AngelVisuals.macroManager:Ldev/angelvisuals/a/by;
        //     87: aload_0
        //     88: new  #11 // dev.angelvisuals.a.H
        //     91: dup
        //     92: invokespecial  #75 // dev.angelvisuals.a.H.<init>:()V
        //     95: putfield  #60 // dev.angelvisuals.AngelVisuals.staffManager:Ldev/angelvisuals/a/H;
        //     98: aload_0
        //     99: new  #13 // dev.angelvisuals.a.ad
        //    102: dup
        //    103: invokespecial  #78 // dev.angelvisuals.a.ad.<init>:()V
        //    106: putfield  #56 // dev.angelvisuals.AngelVisuals.notifyManager:Ldev/angelvisuals/a/ad;
        //    109: aload_0
        //    110: new  #27 // dev.angelvisuals.a.i
        //    113: dup
        //    114: invokespecial  #96 // dev.angelvisuals.a.i.<init>:()V
        //    117: putfield  #59 // dev.angelvisuals.AngelVisuals.serverHandler:Ldev/angelvisuals/a/i;
        //    120: aload_0
        //    121: new  #21 // dev.angelvisuals.a.cM
        //    124: dup
        //    125: invokespecial  #88 // dev.angelvisuals.a.cM.<init>:()V
        //    128: putfield  #57 // dev.angelvisuals.AngelVisuals.rctRepository:Ldev/angelvisuals/a/cM;
        //    131: aload_0
        //    132: new  #23 // dev.angelvisuals.a.ch
        //    135: dup
        //    136: invokespecial  #90 // dev.angelvisuals.a.ch.<init>:()V
        //    139: putfield  #61 // dev.angelvisuals.AngelVisuals.themeManager:Ldev/angelvisuals/a/ch;
        //    142: aload_0
        //    143: new  #26 // dev.angelvisuals.a.e
        //    146: dup
        //    147: invokespecial  #95 // dev.angelvisuals.a.e.<init>:()V
        //    150: putfield  #55 // dev.angelvisuals.AngelVisuals.moduleManager:Ldev/angelvisuals/a/e;
        //    153: aload_0
        //    154: new  #25 // dev.angelvisuals.a.cq
        //    157: dup
        //    158: invokespecial  #93 // dev.angelvisuals.a.cq.<init>:()V
        //    161: putfield  #49 // dev.angelvisuals.AngelVisuals.configManager:Ldev/angelvisuals/a/cq;
        //    164: aload_0
        //    165: new  #16 // dev.angelvisuals.a.bT
        //    168: dup
        //    169: invokespecial  #81 // dev.angelvisuals.a.bT.<init>:()V
        //    172: putfield  #48 // dev.angelvisuals.AngelVisuals.commandManager:Ldev/angelvisuals/a/bT;
        //    175: aload_0
        //    176: new  #10 // dev.angelvisuals.a.E
        //    179: dup
        //    180: invokespecial  #74 // dev.angelvisuals.a.E.<init>:()V
        //    183: putfield  #58 // dev.angelvisuals.AngelVisuals.scriptManager:Ldev/angelvisuals/a/E;
        //    186: aload_0
        //    187: new  #24 // dev.angelvisuals.a.ck
        //    190: dup
        //    191: invokespecial  #91 // dev.angelvisuals.a.ck.<init>:()V
        //    194: putfield  #50 // dev.angelvisuals.AngelVisuals.discordManager:Ldev/angelvisuals/a/ck;
        //    197: goto  206 (offset +9)
        //    200: astore_1
        //    201: aload_0
        //    202: aconst_null
        //    203: putfield  #50 // dev.angelvisuals.AngelVisuals.discordManager:Ldev/angelvisuals/a/ck;
        //    206: aload_0
        //    207: new  #17 // dev.angelvisuals.a.bj
        //    210: dup
        //    211: invokespecial  #82 // dev.angelvisuals.a.bj.<init>:()V
        //    214: putfield  #62 // dev.angelvisuals.AngelVisuals.toggleNotify:Ldev/angelvisuals/a/bj;
        //    217: aload_0
        //    218: new  #15 // dev.angelvisuals.a.bJ
        //    221: dup
        //    222: invokespecial  #80 // dev.angelvisuals.a.bJ.<init>:()V
        //    225: putfield  #63 // dev.angelvisuals.AngelVisuals.waypointManager:Ldev/angelvisuals/a/bJ;
        //    228: aload_0
        //    229: new  #14 // dev.angelvisuals.a.af
        //    232: dup
        //    233: invokespecial  #79 // dev.angelvisuals.a.af.<init>:()V
        //    236: putfield  #54 // dev.angelvisuals.AngelVisuals.menuScreen:Ldev/angelvisuals/a/af;
        //    239: getstatic  #65 // net.minecraft.class_3264.field_14188:Lnet/minecraft/class_3264;
        //    242: invokestatic  #110 // net.fabricmc.fabric.api.resource.ResourceManagerHelper.get:(Lnet/minecraft/class_3264;)Lnet/fabricmc/fabric/api/resource/ResourceManagerHelper;
        //    245: new  #9 // dev.angelvisuals.AngelVisuals$a
        //    248: dup
        //    249: aload_0
        //    250: invokespecial  #73 // dev.angelvisuals.AngelVisuals$a.<init>:(Ldev/angelvisuals/AngelVisuals;)V
        //    253: invokeinterface  #111 // net.fabricmc.fabric.api.resource.ResourceManagerHelper.registerReloadListener:(Lnet/fabricmc/fabric/api/resource/IdentifiableResourceReloadListener;)V, count 2
        //    258: invokestatic  #77 // dev.angelvisuals.a.aE.aW:()V
        //    261: invokestatic  #85 // dev.angelvisuals.a.bu.at:()V
        //    264: invokestatic  #89 // dev.angelvisuals.a.cZ.aS:()V
        //    267: goto  282 (offset +15)
        //    270: astore_1
        //    271: new  #32 // java.lang.RuntimeException
        //    274: dup
        //    275: ldc  #3 // 'AngelVisuals initialization failed'
        //    277: aload_1
        //    278: invokespecial  #104 // java.lang.RuntimeException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    281: athrow
        //    282: return
        //       Exception table:
        //         from 186 to 197 target 200 type java.lang.Throwable
        //         from 13 to 267 target 270 type java.lang.Exception
    }

    @Native
  public void shutdown() {
        friendManager.ae();
        staffManager.ae();
        configManager.ap();
        macroManager.ae();
        if (discordManager != null) {
            discordManager.aP();
        }
    }

  public static class_2960 id(String arg0) {
        return class_2960.method_60655("angelvisuals", arg0);
    }

  public static AngelVisuals getInstance() {
        return INSTANCE;
    }

  public cM getRCTRepository() {
        return rctRepository;
    }

    @Generated
  public ClassA42 getModuleManager() {
        return moduleManager;
    }

    @Generated
  public ch getThemeManager() {
        return themeManager;
    }

    @Generated
  public af getMenuScreen() {
        return menuScreen;
    }

    @Generated
  public ClassA52 getScriptManager() {
        return scriptManager;
    }

    @Generated
  public ClassA145 getServerHandler() {
        return serverHandler;
    }

    @Generated
  public bs getFriendManager() {
        return friendManager;
    }

    @Generated
  public by getMacroManager() {
        return macroManager;
    }

    @Generated
  public ClassA32 getStaffManager() {
        return staffManager;
    }

    @Generated
  public bJ getWaypointManager() {
        return waypointManager;
    }

    @Generated
  public ad getNotifyManager() {
        return notifyManager;
    }

    @Generated
  public bT getCommandManager() {
        return commandManager;
    }

    @Generated
  public cq getConfigManager() {
        return configManager;
    }

    @Generated
  public ck getDiscordManager() {
        return discordManager;
    }

    @Generated
  public bj getToggleNotify() {
        return toggleNotify;
    }

  private static AngelVisuals[] $values() {
        return new AngelVisuals[]{INSTANCE};
    }

  private static void lambda$init$0() {
        getInstance().shutdown();
    }

  private static AngelVisuals[] $values$() {
        return new AngelVisuals[]{INSTANCE};
    }

}