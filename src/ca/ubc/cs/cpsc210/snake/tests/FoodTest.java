package ca.ubc.cs.cpsc210.snake.tests;

import ca.ubc.cs.cpsc210.snake.model.Cell;
import ca.ubc.cs.cpsc210.snake.model.Food;
import ca.ubc.cs.cpsc210.snake.model.SnakeGame;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

// jUnit tests for Food class
public class FoodTest {
    private Food testFood;
    private Food testFood1;

    @Before
    public void runBefore() {

        testFood = new Food(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2));
        testFood1 = new Food(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2), 10);
    }

    @Test
    public void testConstructor() {
        assertEquals(100, testFood.getNutritionalValue());
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2), testFood.getPosition());
        assertEquals(10, testFood1.getNutritionalValue());
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2), testFood1.getPosition());
    }

    @Test
    public void testDecayUnder() {
        testFood.decay();
        assertEquals(90, testFood.getNutritionalValue());
    }

    @Test
    public void testDecayEnough() {
        testFood.decay();
        testFood.decay();
        testFood.decay();
        testFood.decay();
        testFood.decay();
        testFood.decay();
        testFood.decay();
        testFood.decay();
        testFood.decay();
        testFood.decay();
        assertEquals(0, testFood.getNutritionalValue());
    }

    @Test
    public void testDecayOver() {
        testFood.decay();
        testFood.decay();
        testFood.decay();
        testFood.decay();
        testFood.decay();
        testFood.decay();
        testFood.decay();
        testFood.decay();
        testFood.decay();
        testFood.decay();
        testFood.decay();
        assertEquals(0, testFood.getNutritionalValue());
    }
}