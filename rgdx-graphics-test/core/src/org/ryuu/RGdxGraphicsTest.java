package org.ryuu;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import org.ryuu.gdx.graphics.glutils.Material;
import org.ryuu.gdx.scenes.scene2d.ui.MaterialImage;

public class RGdxGraphicsTest extends ApplicationAdapter {
    private Stage stage;

    @Override
    public void create() {
        ExtendViewport extendViewport = new ExtendViewport(1920, 1080);
        stage = new Stage(extendViewport);
        MaterialImage image = new MaterialImage(new Texture("badlogic.jpg"));
        stage.addActor(image);

        ShaderProgram shaderProgram = new ShaderProgram(Gdx.files.internal("hdr.vert"), Gdx.files.internal("hdr.frag"));

        if (!shaderProgram.isCompiled()) {
            Gdx.app.error("shaderProgram", shaderProgram.getLog());
        }

        String HDR_COLOR_ATTRIBUTE = "a_hdrColor";
        String INTENSITY_ATTRIBUTE = "a_intensity";

        image.setMaterial(new Material());
        image.getMaterial().setShaderProgram(shaderProgram);
        image.getMaterial().setAttributef(HDR_COLOR_ATTRIBUTE, 1, 1, 1, 1);
        image.getMaterial().setAttributef(INTENSITY_ATTRIBUTE, 1, 0, 0, 0);
    }

    @Override
    public void render() {
        stage.act();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
