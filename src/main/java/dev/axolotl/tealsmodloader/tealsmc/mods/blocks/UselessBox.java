//Lines 2-11 are just importing things
package dev.axolotl.tealsmodloader.tealsmc.mods.blocks;
import dev.axolotl.tealsmodloader.TestMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import dev.axolotl.tealsmodloader.tealsmodloader.Common;
import java.util.Random;
//Class header
public class UselessBox extends Block {
    //Array of IIcons to store the textures
    private IIcon[] texture;
    //Constructor (no parameters)
    public UselessBox() {
        //Useless boxes are of the circuit material
        super(Material.circuits);
        //Useless boxes are in the redstone tab
        setCreativeTab(TestMod.tabTestMod);
    }
    //Registering the textures
    public void registerBlockIcons(IIconRegister iconRegister) {
        texture = new IIcon[4];
        texture[0] = iconRegister.registerIcon(Common.MOD_ID + ":useless_bottom");
        texture[1] = iconRegister.registerIcon(Common.MOD_ID + ":useless_top");
        texture[2] = iconRegister.registerIcon(Common.MOD_ID + ":useless_face");
        texture[3] = iconRegister.registerIcon(Common.MOD_ID + ":useless_side");
    }
    //Retrieving the textures for a given side
    public IIcon getIcon(int side, int meta) {
        if(side >= 3) return texture[3];
        return texture[side];
    }
    //If a block is ever providing power to any of the useless block's sides, it temporarily sets that block to air
    public void randomDisplayTick(World world, int x, int y, int z, Random r) {
        if(world.isBlockProvidingPowerTo(x, y, z+1, 1) > 0) world.setBlock(x, y, z+1, Blocks.air);
        if(world.isBlockProvidingPowerTo(x, y, z-1, 2) > 0) world.setBlock(x, y, z-1, Blocks.air);
        if(world.isBlockProvidingPowerTo(x+1, y, z, 5) > 0) world.setBlock(x+1, y, z, Blocks.air);
        if(world.isBlockProvidingPowerTo(x-1, y, z, 4) > 0) world.setBlock(x-1, y, z, Blocks.air);
    }
}
