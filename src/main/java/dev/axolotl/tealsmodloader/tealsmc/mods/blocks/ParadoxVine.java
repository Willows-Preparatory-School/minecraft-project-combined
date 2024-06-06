package dev.axolotl.tealsmodloader.tealsmc.mods.blocks;

import dev.axolotl.tealsmodloader.tealsmodloader.Common;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

public class ParadoxVine extends Block {
    private int id; //0=base, 1=normal, 2=horizontal x, 3=horizontal z, 4=+xc, 5=-xc, 6=+zc,
                          // 7=-zc, 8=+xi, 9=-xi, 10=+zi, 11=-zi, 12=top
                    // _c = corner on the branch extending in the direction
                    // _i = intersection on main vine extending in the direction
                    // used as an index in icons
    private static IIcon[][] icons = new IIcon[13][6];
    public ParadoxVine(int i) {
        super(Material.rock);
        id = i;
        if(id==0) {
            setCreativeTab(CreativeTabs.tabBlock);
        }
    }
    public void registerBlockIcons(IIconRegister iconRegister){
        icons[0][0] = iconRegister.registerIcon(Common.MOD_ID + ":vine_top");
        icons[0][1] = iconRegister.registerIcon(Common.MOD_ID + ":vine_inside");
        for(int i=2;i<6;i++) {
            icons[0][i] = iconRegister.registerIcon(Common.MOD_ID + ":vine_base");
        }

        icons[1][0] = iconRegister.registerIcon(Common.MOD_ID + ":vine_inside");
        icons[1][1] = iconRegister.registerIcon(Common.MOD_ID + ":vine_inside");
        for(int i=2;i<6;i++){
            icons[1][i] = iconRegister.registerIcon(Common.MOD_ID + ":vine_side_vertical");
        }

        for(int i=0;i<4;i++) {
            icons[2][i] = iconRegister.registerIcon(Common.MOD_ID + ":vine_horizontal");
        }
        for(int i=4;i<6;i++){
            icons[2][i] = iconRegister.registerIcon(Common.MOD_ID + ":vine_inside");
        }

        icons[3][0] = iconRegister.registerIcon(Common.MOD_ID + ":vine_horizontal");
        icons[3][1] = iconRegister.registerIcon(Common.MOD_ID + ":vine_horizontal");
        icons[3][2] = iconRegister.registerIcon(Common.MOD_ID + ":vine_inside");
        icons[3][3] = iconRegister.registerIcon(Common.MOD_ID + ":vine_inside");
        icons[3][4] = iconRegister.registerIcon(Common.MOD_ID + ":vine_horizontal");
        icons[3][5] = iconRegister.registerIcon(Common.MOD_ID + ":vine_horizontal");

        icons[4][0] = iconRegister.registerIcon(Common.MOD_ID + ":vine_horizontal");
        icons[4][1] = iconRegister.registerIcon(Common.MOD_ID + ":vine_inside");
        icons[4][2] = iconRegister.registerIcon(Common.MOD_ID + ":vine_right_corner");
        icons[4][3] = iconRegister.registerIcon(Common.MOD_ID + ":vine_left_corner");
        icons[4][4] = iconRegister.registerIcon(Common.MOD_ID + ":vine_inside");
        icons[4][5] = iconRegister.registerIcon(Common.MOD_ID + ":vine_side_vertical");

        icons[5][0] = iconRegister.registerIcon(Common.MOD_ID + ":vine_horizontal");
        icons[5][1] = iconRegister.registerIcon(Common.MOD_ID + ":vine_inside");
        icons[5][2] = iconRegister.registerIcon(Common.MOD_ID + ":vine_left_corner");
        icons[5][3] = iconRegister.registerIcon(Common.MOD_ID + ":vine_right_corner");
        icons[5][4] = iconRegister.registerIcon(Common.MOD_ID + ":vine_side_vertical");
        icons[5][5] = iconRegister.registerIcon(Common.MOD_ID + ":vine_inside");

        icons[6][0] = iconRegister.registerIcon(Common.MOD_ID + ":vine_side_vertical");
        icons[6][1] = iconRegister.registerIcon(Common.MOD_ID + ":vine_inside");
        icons[6][2] = iconRegister.registerIcon(Common.MOD_ID + ":vine_inside");
        icons[6][3] = iconRegister.registerIcon(Common.MOD_ID + ":vine_side_vertical");
        icons[6][4] = iconRegister.registerIcon(Common.MOD_ID + ":vine_left_corner");
        icons[6][5] = iconRegister.registerIcon(Common.MOD_ID + ":vine_right_corner");

        icons[7][0] = iconRegister.registerIcon(Common.MOD_ID + ":vine_side_vertical");
        icons[7][1] = iconRegister.registerIcon(Common.MOD_ID + ":vine_inside");
        icons[7][2] = iconRegister.registerIcon(Common.MOD_ID + ":vine_side_vertical");
        icons[7][3] = iconRegister.registerIcon(Common.MOD_ID + ":vine_inside");
        icons[7][4] = iconRegister.registerIcon(Common.MOD_ID + ":vine_right_corner");
        icons[7][5] = iconRegister.registerIcon(Common.MOD_ID + ":vine_left_corner");

        icons[8][0] = iconRegister.registerIcon(Common.MOD_ID + ":vine_inside");
        icons[8][1] = iconRegister.registerIcon(Common.MOD_ID + ":vine_inside");
        icons[8][2] = iconRegister.registerIcon(Common.MOD_ID + ":vine_left_growth");
        icons[8][3] = iconRegister.registerIcon(Common.MOD_ID + ":vine_right_growth");
        icons[8][4] = iconRegister.registerIcon(Common.MOD_ID + ":vine_side_vertical");
        icons[8][5] = iconRegister.registerIcon(Common.MOD_ID + ":vine_inside");

        icons[9][0] = iconRegister.registerIcon(Common.MOD_ID + ":vine_inside");
        icons[9][1] = iconRegister.registerIcon(Common.MOD_ID + ":vine_inside");
        icons[9][2] = iconRegister.registerIcon(Common.MOD_ID + ":vine_right_growth");
        icons[9][3] = iconRegister.registerIcon(Common.MOD_ID + ":vine_left_growth");
        icons[9][4] = iconRegister.registerIcon(Common.MOD_ID + ":vine_inside");
        icons[9][5] = iconRegister.registerIcon(Common.MOD_ID + ":vine_side_vertical");

        icons[10][0] = iconRegister.registerIcon(Common.MOD_ID + ":vine_inside");
        icons[10][1] = iconRegister.registerIcon(Common.MOD_ID + ":vine_inside");
        icons[10][2] = iconRegister.registerIcon(Common.MOD_ID + ":vine_side_vertical");
        icons[10][3] = iconRegister.registerIcon(Common.MOD_ID + ":vine_inside");
        icons[10][4] = iconRegister.registerIcon(Common.MOD_ID + ":vine_left_growth");
        icons[10][5] = iconRegister.registerIcon(Common.MOD_ID + ":vine_right_growth");

        icons[11][0] = iconRegister.registerIcon(Common.MOD_ID + ":vine_inside");
        icons[11][1] = iconRegister.registerIcon(Common.MOD_ID + ":vine_inside");
        icons[11][2] = iconRegister.registerIcon(Common.MOD_ID + ":vine_inside");
        icons[11][3] = iconRegister.registerIcon(Common.MOD_ID + ":vine_side_vertical");
        icons[11][4] = iconRegister.registerIcon(Common.MOD_ID + ":vine_right_growth");
        icons[11][5] = iconRegister.registerIcon(Common.MOD_ID + ":vine_left_growth");

        for(int j=0;j<6;j++){
            icons[12][j] = iconRegister.registerIcon(Common.MOD_ID + ":vine_top");
        }
        icons[12][0] = iconRegister.registerIcon(Common.MOD_ID + ":vine_inside");
    }
    public IIcon getIcon(int side, int meta){
        //0 = bottom, 1 = top, 2 = -z, 3 = +z,4 = -x, 5 = +x
        return icons[id][side];
    }
    public int getId(){
        return id;
    }
    public void setId(int id1){
        id = id1;
    }
}
