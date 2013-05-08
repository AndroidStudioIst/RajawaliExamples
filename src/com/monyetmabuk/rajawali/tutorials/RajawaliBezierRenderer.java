package com.monyetmabuk.rajawali.tutorials;

import rajawali.BaseObject3D;
import rajawali.animation.Animation3D;
import rajawali.animation.Animation3D.RepeatMode;
import rajawali.animation.BezierPath3D;
import rajawali.animation.TranslateAnimation3D;
import rajawali.lights.DirectionalLight;
import rajawali.materials.DiffuseMaterial;
import rajawali.materials.PhongMaterial;
import rajawali.math.Number3D;
import rajawali.primitives.Sphere;
import rajawali.renderer.RajawaliRenderer;
import android.content.Context;

public class RajawaliBezierRenderer extends RajawaliRenderer {
	private DirectionalLight mLight;

	public RajawaliBezierRenderer(Context context) {
		super(context);
		setFrameRate(60);
	}

	protected void initScene() {
		mLight = new DirectionalLight(0, 1, -1);
		mLight.setPower(1);
		getCurrentCamera().setPosition(0, 0, 20);

		BaseObject3D redSphere = new Sphere(1, 16, 16);
		redSphere.addLight(mLight);
		redSphere.setPosition(0, -4, 0);
		redSphere.setColor(0xffff0000);
		
		PhongMaterial phong = new PhongMaterial();
		phong.setUseColor(true);
		redSphere.setMaterial(phong);
		addChild(redSphere);

		BaseObject3D yellowSphere = new Sphere(.6f, 16, 16);
		yellowSphere.addLight(mLight);
		yellowSphere.setPosition(2, 4, 0);
		yellowSphere.setColor(0xffffff00);
		DiffuseMaterial diffuse = new DiffuseMaterial();
		diffuse.setUseColor(true);
		yellowSphere.setMaterial(diffuse);
		addChild(yellowSphere);

		BezierPath3D redBezierPath = new BezierPath3D();
		redBezierPath.addPoint(new Number3D(0, -4, 0), new Number3D(-2, -4, .2f), new Number3D(4, 4, 4), new Number3D(-2, 4, 4.5f));
		redBezierPath.addPoint(new Number3D(-2, 4, 4.5f), new Number3D(2, -2, -2), new Number3D(4, 4, 4), new Number3D(-2, 4, 4.5f));

		BezierPath3D yellowBezierPath = new BezierPath3D();
		yellowBezierPath.addPoint(new Number3D(2, 4, 0), new Number3D(-8, 3, 4), new Number3D(-4, 0, -2), new Number3D(4, -3, 30));
		yellowBezierPath.addPoint(new Number3D(4, -3, 30), new Number3D(6, 1, 2), new Number3D(4, 2, 3), new Number3D(-3, -3, -4.5f));

		Animation3D redAnim = new TranslateAnimation3D(redBezierPath);
		redAnim.setDuration(2000);
		redAnim.setRepeatMode(RepeatMode.REVERSE_INFINITE);
		redAnim.setTransformable3D(redSphere);
		registerAnimation(redAnim);
		redAnim.play();

		Animation3D yellowAnim = new TranslateAnimation3D(yellowBezierPath);
		yellowAnim.setDuration(3800);
		yellowAnim.setRepeatMode(RepeatMode.REVERSE_INFINITE);
		yellowAnim.setTransformable3D(yellowSphere);
		registerAnimation(yellowAnim);
		yellowAnim.play();
	}
}
