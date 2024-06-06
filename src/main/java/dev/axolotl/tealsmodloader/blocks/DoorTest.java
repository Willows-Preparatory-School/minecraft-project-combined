package dev.axolotl.tealsmodloader.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dev.axolotl.tealsmodloader.TestMod;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class DoorTest extends BlockDoor
{
    @SideOnly(Side.CLIENT)
    private IIcon[] doorUpper; // field_150017_a
    @SideOnly(Side.CLIENT)
    private IIcon[] doorLower; // field_150016_b

    public DoorTest()
    {
        super(Material.wood);
        this.setCreativeTab(TestMod.tabTestMod);
        this.setBlockTextureName("doors/cleanroom_door");
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.doorLower[0];
    }

    @Override
    public IIcon getIcon(IBlockAccess worldIn, int x, int y, int z, int side)
    {
        if (side != 1 && side != 0)
        {
            int i1 = this.func_150012_g(worldIn, x, y, z);
            int j1 = i1 & 3;
            boolean flag = (i1 & 4) != 0;
            boolean flag1 = false;
            boolean flag2 = (i1 & 8) != 0;

            if (flag)
            {
                if (j1 == 0 && side == 2)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 1 && side == 5)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 2 && side == 3)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 3 && side == 4)
                {
                    flag1 = !flag1;
                }
            }
            else
            {
                if (j1 == 0 && side == 5)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 1 && side == 3)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 2 && side == 4)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 3 && side == 2)
                {
                    flag1 = !flag1;
                }

                if ((i1 & 16) != 0)
                {
                    flag1 = !flag1;
                }
            }

            return flag2 ? this.doorUpper[flag1?1:0] : this.doorLower[flag1?1:0];
        }
        else
        {
            return this.doorLower[0];
        }
    }

    @Override
    public void registerBlockIcons(IIconRegister reg)
    {
        this.doorUpper = new IIcon[2];
        this.doorLower = new IIcon[2];
        this.doorUpper[0] = reg.registerIcon(TestMod.MODID + ":" + this.getTextureName() + "_upper");
        this.doorLower[0] = reg.registerIcon(TestMod.MODID + ":" + this.getTextureName() + "_lower");
        this.doorUpper[1] = new IconFlipped(this.doorUpper[0], true, false);
        this.doorLower[1] = new IconFlipped(this.doorLower[0], true, false);
    }
}
