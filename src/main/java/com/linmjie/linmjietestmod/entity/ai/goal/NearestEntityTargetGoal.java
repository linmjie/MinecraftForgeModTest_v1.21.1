package com.linmjie.linmjietestmod.entity.ai.goal;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.AABB;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class NearestEntityTargetGoal<T extends Entity> extends Goal {

    protected final Mob mob;
    protected final Class<T> targetClass;
    protected final double range;
    protected final Predicate<? super T> targetFilter;
    protected T target;

    public NearestEntityTargetGoal(Mob mob, Class<T> targetClass, double range, Predicate<? super T> targetFilter) {
        this.mob = mob;
        this.targetClass = targetClass;
        this.range = range;
        this.targetFilter = targetFilter;
        this.setFlags(EnumSet.of(Goal.Flag.TARGET, Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        AABB searchBox = this.mob.getBoundingBox().inflate(range, 4.0, range);
        List<T> potentialTargets = this.mob.level().getEntitiesOfClass(this.targetClass, searchBox, targetFilter);

        if (!potentialTargets.isEmpty()) {
            potentialTargets.sort((a, b) -> Double.compare(a.distanceToSqr(mob), b.distanceToSqr(mob)));
            this.target = potentialTargets.get(0);
            return true;
        }

        return false;
    }

    @Override
    public void start() {
        if (this.target != null) {
            this.mob.getNavigation().moveTo(target, 1.4);
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.target != null && this.target.isAlive() && this.mob.distanceToSqr(this.target) < (range * range);
    }

    @Override
    public void stop() {
        this.target = null;
    }

    public T getTarget() {
        return this.target;
    }
}
