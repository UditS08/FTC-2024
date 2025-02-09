package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.hardware.DcMotor;

public class MotorPair {
    public final DcMotor left, right;
    private int minPosition, maxPosition;
    private float power;
    private int currentTargetPos;
    private int graduatedVelocity = 0;

    public MotorPair(int minPosition, int maxPosition, float power, DcMotor left, DcMotor right) {
        this(minPosition, maxPosition, power, left, right, DcMotor.Direction.FORWARD, DcMotor.Direction.REVERSE);
    }

    public MotorPair(int minPosition, int maxPosition, float power, DcMotor left, DcMotor right, DcMotor.Direction direction) {
        this(minPosition, maxPosition, power, left, right, direction, direction);
    }

    public MotorPair(int minPosition, int maxPosition, float power, DcMotor left, DcMotor right, DcMotor.Direction leftDirection, DcMotor.Direction rightDirection) {
        this.minPosition = minPosition;
        this.maxPosition = maxPosition;
        this.currentTargetPos = this.minPosition;
        this.power = power;
        this.left = left;
        this.right = right;
        this.left.setDirection(leftDirection);
        this.right.setDirection(rightDirection);
        this.left.setTargetPosition(0);
        this.right.setTargetPosition(0);
        this.left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.left.setPower(this.power);
        this.right.setPower(this.power);
    }

    public float getPower() {
        return this.power;
    }

    public void setPower(float power) {
        this.power = Math.min(Math.max(power, 0), 1);
        this.left.setPower(this.power);
        this.right.setPower(this.power);
    }

    public int getMinPosition() {
        return this.minPosition;
    }

    public int getMaxPosition() {
        return this.maxPosition;
    }

    public void setMaxPosition(int pos) {
        this.maxPosition = pos;
        if (this.maxPosition < this.currentTargetPos) {
            this.setPosition(this.currentTargetPos);
        }
    }

    public void setGraduatedVelocity(int vel) {
        this.graduatedVelocity = vel;
    }

    public void resetPosition() {
        this.left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.setPosition(this.minPosition);
    }

    public int getTargetPosition() {
        return this.currentTargetPos;
    }

    public void setPosition(int pos) {
        this.currentTargetPos = Math.min(Math.max(pos, this.minPosition), this.maxPosition);
    }

    public void move(int pos) {
        this.setPosition(this.currentTargetPos + pos);
    }

    public int getLeftPosition() {
        return this.left.getCurrentPosition();
    }

    public int getRightPosition() {
        return this.right.getCurrentPosition();
    }

    public void update() {
        // if (this.graduatedVelocity == 0) {
            this.left.setTargetPosition(this.currentTargetPos);
            this.right.setTargetPosition(this.currentTargetPos);
        // } else {
        //     int leftTarget = this.left.getTargetPosition();
        //     int rightTarget = this.right.getTargetPosition();
        //     int leftNextTarget = leftTarget, rightNextTarget = rightTarget;
        //     if (leftTarget != this.currentTargetPos) {
        //         int leftPos = this.left.getCurrentPosition();
        //         if (Math.abs(leftPos - leftTarget) < this.graduatedVelocity) {
        //             if (leftPos > this.currentTargetPos) {
        //                 leftNextTarget = Math.max(leftTarget - this.graduatedVelocity, this.currentTargetPos);
        //             } else if (leftPos < this.currentTargetPos) {
        //                 leftNextTarget = Math.max(leftTarget + this.graduatedVelocity, this.currentTargetPos);
        //             }
        //         }
        //     }
        //     if (rightTarget != this.currentTargetPos) {
        //         int rightPos = this.right.getCurrentPosition();
        //         if (Math.abs(rightPos - rightTarget) < this.graduatedVelocity) {
        //             if (rightPos > this.currentTargetPos) {
        //                 rightNextTarget = Math.max(rightTarget - this.graduatedVelocity, this.currentTargetPos);
        //             } else if (rightPos < this.currentTargetPos) {
        //                 rightNextTarget = Math.max(rightTarget + this.graduatedVelocity, this.currentTargetPos);
        //             }
        //         }
        //     }
        //     if (rightNextTarget > rightTarget) {
        //         rightNextTarget = Math.min(rightNextTarget, leftNextTarget);
        //     } else if (rightNextTarget < rightTarget) {
        //         rightNextTarget = Math.max(rightNextTarget, leftNextTarget);
        //     }
        //     if (leftNextTarget > leftTarget) {
        //         leftNextTarget = Math.min(leftNextTarget, rightNextTarget);
        //     } else if (leftNextTarget < leftTarget) {
        //         leftNextTarget = Math.max(leftNextTarget, rightNextTarget);
        //     }
        //     if (leftTarget != leftNextTarget) {
        //         this.left.setTargetPosition(leftNextTarget);
        //     }
        //     if (rightTarget != rightNextTarget) {
        //         this.right.setTargetPosition(rightNextTarget);
        //     }
        // }
        this.left.setPower(this.power);
        this.right.setPower(this.power);
        this.left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
