//The package it is in
package dev.axolotl.tealsmodloader.tealsmc.mods.blocks;

//The packages necessary to work with Minecraft
import dev.axolotl.tealsmodloader.TestMod;
import net.minecraft.block.BlockWall;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import dev.axolotl.tealsmodloader.tealsmodloader.Common;

//For shape
public class Explocharge extends BlockWall {

    //The textures
    private IIcon[] texture = new IIcon[2];

    //The mod's only explosion size
    public static final int explosionSize = 5;

    //Constructor

    public Explocharge(){

        //Failsafe setting the texture to tnt, also necessary for class constructor
        super(Blocks.tnt);

        //Puts it in the redstone tab
        setCreativeTab(TestMod.tabTestMod);
    }

    //Gets the textures
    public void registerBlockIcons(IIconRegister iconRegister) {
        //Not the redstone texture, my own
        texture[0] = iconRegister.registerIcon(Common.MOD_ID + ":bloww_side");

        //NOT MY TEXTURE, minecraft's
        texture[1] = iconRegister.registerIcon(Common.MOD_ID + ":bloww_top");
    }

    //Delineates Texture sides
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        if (! (p_149691_1_ == 1)){
            //Top
            return texture[0];
        } else {
            //Rest
            return texture[1];
        }
    }

    //Makes it so that it doesn't connect to other walls, retaining its shape
    public boolean canConnectWallTo(IBlockAccess iBlockAccess, int x, int y, int z) {
        return false;
    }

    //When the block is exploded by a normal explosion
    public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion) {
        //So it loads in the world correctly and saves
        if (!world.isRemote) {

            //A new lit tnt entity at the block location, the explosion is inputted so it can show up on the death screen who lit the explosion if the explosion kills
            EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(world, ((float)x + 0.5F), ((float)y + 0.5F), ((float)z + 0.5F), explosion.getExplosivePlacedBy());

            //Makes it so that it explodes almost instantly, a few milliseconds
            entitytntprimed.fuse = 4;

            //Spawns entity at location
            world.spawnEntityInWorld(entitytntprimed);
        }
    }

    //So while it is the primary block behind Detonator.
    //If it is exploded by any other thing than it can set off a fast chain of explosions,
    //without the physics problems of normal TNT, making it applicable in more situations.
    //It is also safer to use as it can only be set off by explosions, not fire or redstone.
}
