package me.CarsCupcake.SkyblockRemake.utils;

import java.util.Optional;

import org.bukkit.block.Block;

import net.minecraft.util.MathHelper;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.ai.control.ControllerLook;



public class BlockLookController extends ControllerLook {

    private Block lookingAt;
    public BlockLookController(EntityInsentient entity,Block lookingAt) {
        super(entity);
        this.lookingAt=lookingAt;
    }
    //use this method to change block the village is looking at
    public void changeLookingBlock(Block lookingAt) {
        this.lookingAt=lookingAt;
    }
    @Override
    public void a(double lookX, double lookY, double lookZ, float var6, float var7) {
        this.e = lookingAt.getX();
        this.f = lookingAt.getY();
        this.g = lookingAt.getZ();
        this.b = 10; //these are look speed i guess
        this.c = 10;    //
        this.d = true;
    }
    @Override
    protected void b() {
    	if (!this.a.getNavigation().m()) {
            this.a.aZ = MathHelper.c(this.a.aZ, this.a.aX, (float)this.a.fa());
        }
    }
    @Override
    public boolean c() {
        return true;
    }
    @Override
    public boolean d() {
        return this.d;
    }
    @Override
    public double e() {
        return this.lookingAt.getY();
    }
    @Override
    public double f() {
        return this.lookingAt.getZ();
    }
    @Override
	public double g() {
        double var0 = this.lookingAt.getX() - this.a.locX();
        double var2 = this.lookingAt.getY() - this.a.getHeadY();
        double var4 = this.lookingAt.getZ() - this.a.locZ();
        double var6 = Math.sqrt(var0 * var0 + var4 * var4);
        return (float)-(MathHelper.d(var2, var6) * 57.2957763671875D);
    }
    @Override
    protected Optional<Float> h() {
        double var0 = this.lookingAt.getX() - this.a.locX();
        double var2 = this.lookingAt.getZ() - this.a.locZ();
        return Optional.of((float)(MathHelper.d(var2, var0) * 57.2957763671875D) - 90.0F);
    }
    @Override
    protected float a(float var0, float var1, float var2) {
        float var3 = MathHelper.c(var0, var1);
        float var4 = MathHelper.a(var3, -var2, var2);
        return var0 + var4;
    }
}
