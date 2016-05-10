package com.udacity.gamedev.drawthecantorgasket;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

/*

TODO: Start here

The Cantor gasket is a fractal where we start with a white square. We divide that square up into a 3x3 grid of smaller squares, then remove the middle square. Finally, we repeat the process on each of the remaining 8 squares.

 */

public class DrawTheCantorGasket extends ApplicationAdapter {

    ShapeRenderer shapeRenderer;
    // TODO: Set a constant for how many recursions to draw. 5 is a good place to start
    public static final int recursions = 6;

    @Override
    public void create () {
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Finds a good place to draw our fractal
        // Rectangle has members x,y for the lower left corner, and width and height
        Rectangle bounds = findLargestSquare();

        // TODO: Begin a filled shapeRenderer batch
        shapeRenderer.begin(ShapeType.Filled);

        // TODO: Draw a white square matching the bounds
        float x = bounds.getX();
        float y = bounds.getY();
        float size = bounds.getWidth();

        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(x, y, size, size);

        // TODO: Set the working color to black, and call punchCantorGasket with the bounds
        shapeRenderer.setColor(Color.BLACK);
        punchCantorGasket(x, y, size, recursions);

        // TODO: End the batch
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        shapeRenderer = new ShapeRenderer();
    }


    private void punchCantorGasket(float x, float y, float size, int recursions){
        // Note that size means the height and width of the square
        // TODO: Base case, if recursions = 0, return
        if (recursions == 0) {
            return;
        }

        // TODO: Draw a black square in the middle square
        float punchSize = size / 3;
        float gasketCornerX = x + punchSize;
        float gasketCornerY = y + punchSize;
        shapeRenderer.rect(gasketCornerX, gasketCornerY, punchSize, punchSize);

        // TODO: Call punchCantorGasket on all 8 other squares
        // go counter clockwise starting in bottom left corner
        float x1 = x;
        float y1 = y;
        float x2 = x + punchSize;
        float y2 = y;
        float x3 = x + punchSize * 2;
        float y3 = y;
        float x4 = x3;
        float y4 = y + punchSize;
        float x5 = x4;
        float y5 = y + punchSize * 2;
        float x6 = x2;
        float y6 = y5;
        float x7 = x;
        float y7 = y6;
        float x8 = x;
        float y8 = y4;
        punchCantorGasket(x1, y1, punchSize, recursions - 1);
        punchCantorGasket(x2, y2, punchSize, recursions - 1);
        punchCantorGasket(x3, y3, punchSize, recursions - 1);
        punchCantorGasket(x4, y4, punchSize, recursions - 1);
        punchCantorGasket(x5, y5, punchSize, recursions - 1);
        punchCantorGasket(x6, y6, punchSize, recursions - 1);
        punchCantorGasket(x7, y7, punchSize, recursions - 1);
        punchCantorGasket(x8, y8, punchSize, recursions - 1);
    }

    private Rectangle findLargestSquare(){
        Rectangle largestSquare = new Rectangle();
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        if (screenWidth > screenHeight){
            largestSquare.x = (screenWidth - screenHeight)/2;
            largestSquare.y = 0;
            largestSquare.width = screenHeight;
            largestSquare.height = screenHeight;
        } else {
            largestSquare.x = 0;
            largestSquare.y = (screenHeight - screenWidth)/2;
            largestSquare.width = screenWidth;
            largestSquare.height = screenWidth;
        }
        return largestSquare;
    }
}
