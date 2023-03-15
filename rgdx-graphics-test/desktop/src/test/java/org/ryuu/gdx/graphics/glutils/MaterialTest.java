package org.ryuu.gdx.graphics.glutils;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ryuu.functional.Actions;
import org.ryuu.gdx.scenes.scene2d.ui.MaterialImage;

public class MaterialTest {
    private ApplicationListener applicationListener;

    private Stage stage;

    private final Actions afterStageCreate = new Actions();

    @BeforeEach
    void create() {
        applicationListener = new ApplicationAdapter() {
            @Override
            public void create() {
                ExtendViewport extendViewport = new ExtendViewport(1920, 1080);
                stage = new Stage(extendViewport);
                afterStageCreate.invoke();
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
        };
    }

    @AfterEach
    void launch() {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(1920, 1080);
        config.setTitle("rgdx-graphics-test");
        new Lwjgl3Application(applicationListener, config);
    }

    @Test
    void setShaderProgram() {
        afterStageCreate.add(() -> {
            MaterialImage image = new MaterialImage(new Texture("badlogic.jpg"));
            stage.addActor(image);

            MaterialImage materialImage = new MaterialImage(new Texture("badlogic.jpg"));
            stage.addActor(materialImage);
            materialImage.moveBy(materialImage.getWidth(), 0);

            ShaderProgram shaderProgram = new ShaderProgram(Gdx.files.internal("grayScale.vert"), Gdx.files.internal("grayScale.frag"));

            if (!shaderProgram.isCompiled()) {
                Gdx.app.error("shaderProgram", shaderProgram.getLog());
            }

            materialImage.setMaterial(new Material());
            materialImage.getMaterial().setShaderProgram(shaderProgram);
        });
    }

    @Test
    void setAttributef() {
        afterStageCreate.add(() -> {
            MaterialImage image = new MaterialImage(new Texture("badlogic.jpg"));
            stage.addActor(image);

            MaterialImage materialImage = new MaterialImage(new Texture("badlogic.jpg"));
            stage.addActor(materialImage);
            materialImage.moveBy(materialImage.getWidth(), 0);

            ShaderProgram shaderProgram = new ShaderProgram(Gdx.files.internal("hdr.vert"), Gdx.files.internal("hdr.frag"));

            if (!shaderProgram.isCompiled()) {
                Gdx.app.error("shaderProgram", shaderProgram.getLog());
            }

            materialImage.setMaterial(new Material());
            materialImage.getMaterial().setShaderProgram(shaderProgram);
            materialImage.getMaterial().setAttributef("a_hdrColor", 1, 1, 1, 1);
            materialImage.getMaterial().setAttributef("a_intensity", 2, 0, 0, 0);
        });
    }
}
