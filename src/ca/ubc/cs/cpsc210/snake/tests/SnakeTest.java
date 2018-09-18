package ca.ubc.cs.cpsc210.snake.tests;

import ca.ubc.cs.cpsc210.snake.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

// jUnit tests for Snake class
public class SnakeTest {
    private Snake testSnake;

    @Before
    public void runBefore() {
        testSnake = new Snake(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2));
    }

    @Test
    public void testConstructor() {
        assertEquals(1, testSnake.length());
        assertFalse(testSnake.canGrow());
    }

    @Test
    public void testMoveRight() {
        setSnakeDirection(testSnake, Direction.RIGHT);
        testSnake.move();
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2 + 1), testSnake.getPosition());
    }

    @Test
    public void testMoveLeft() {
        setSnakeDirection(testSnake, Direction.LEFT);
        testSnake.move();
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2 - 1), testSnake.getPosition());
    }

    @Test
    public void testMoveUp() {
        setSnakeDirection(testSnake, Direction.UP);
        testSnake.move();
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2 - 1, SnakeGame.BOARD_COLS / 2), testSnake.getPosition());
    }

    @Test
    public void testMoveDown() {
        setSnakeDirection(testSnake, Direction.DOWN);
        testSnake.move();
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2 + 1, SnakeGame.BOARD_COLS / 2), testSnake.getPosition());
    }

    @Test
    public void testMoveBody() {
        setSnakeDirection(testSnake, Direction.RIGHT);
        growBodyByTwo();
        testSnake.move();
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2 + 3), testSnake.getPosition());

        List<Cell> body = testSnake.getBodyPositions();
        assertEquals(2, body.size());
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2 + 2), body.get(0));
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2 + 1), body.get(1));
    }

    @Test
    public void testGrowOnFeed() {
        Food food = new Food(testSnake.getPosition(), Snake.NUTRITION_TO_GROW);
        testSnake.eat(food);
        assertEquals(1, testSnake.length());
        assertTrue(testSnake.canGrow());

        testSnake.move();
        assertEquals(2, testSnake.length());
        assertFalse(testSnake.canGrow());
    }

    @Test
    public void testGrowOnManyFeed() {
        Food food = new Food(testSnake.getPosition(), Snake.NUTRITION_TO_GROW);
        testSnake.eat(food);
        testSnake.move();
        testSnake.eat(food);
        testSnake.move();
        testSnake.eat(food);
        testSnake.move();
        testSnake.eat(food);
        testSnake.move();
        testSnake.eat(food);
        testSnake.move();
        assertEquals(6, testSnake.length());
        assertFalse(testSnake.canGrow());
    }

    @Test
    public void testEatEatOnFeed() {
        Food food = new Food(testSnake.getPosition(), Snake.NUTRITION_TO_GROW);
        testSnake.eat(food);
        testSnake.eat(food);
        testSnake.move();
        assertEquals(2, testSnake.length());
        assertTrue(testSnake.canGrow());
    }

    @Test
    public void testCanGrowOnFeedRightMoveHead() {
        setSnakeDirection(testSnake, Direction.RIGHT);
        Food food = new Food(testSnake.getPosition(), 50);
        testSnake.eat(food);
        testSnake.move();
        assertEquals(2, testSnake.length());
        assertFalse(testSnake.canGrow());
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2 + 1), testSnake.getPosition());
    }

    @Test
    public void testCanGrowOnFeedLeftMoveHead() {
        setSnakeDirection(testSnake, Direction.LEFT);
        Food food = new Food(testSnake.getPosition(), 50);
        testSnake.eat(food);
        testSnake.move();
        assertEquals(2, testSnake.length());
        assertFalse(testSnake.canGrow());
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2 - 1), testSnake.getPosition());
    }

    @Test
    public void testCanGrowOnFeedUpMoveHead() {
        setSnakeDirection(testSnake, Direction.UP);
        Food food = new Food(testSnake.getPosition(), 50);
        testSnake.eat(food);
        testSnake.move();
        assertEquals(2, testSnake.length());
        assertFalse(testSnake.canGrow());
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2 - 1, SnakeGame.BOARD_COLS / 2), testSnake.getPosition());
    }

    @Test
    public void testCanGrowOnFeedDownMoveHead() {
        setSnakeDirection(testSnake, Direction.DOWN);
        Food food = new Food(testSnake.getPosition(), 50);
        testSnake.eat(food);
        testSnake.move();
        assertEquals(2, testSnake.length());
        assertFalse(testSnake.canGrow());
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2 + 1, SnakeGame.BOARD_COLS / 2), testSnake.getPosition());
    }

    @Test
    public void testCantGrowOnFeed() {
        Food food = new Food(testSnake.getPosition(), 30);
        testSnake.eat(food);
        testSnake.move();
        assertEquals(1, testSnake.length());
        assertFalse(testSnake.canGrow());
    }




    @Test
    public void testLeftRotateLeft() {
        setSnakeDirection(testSnake, Direction.LEFT);
        testSnake.rotateLeft();
        assertEquals(Direction.DOWN, testSnake.getDirection());
    }

    @Test
    public void testRightRotateLeft() {
        setSnakeDirection(testSnake, Direction.RIGHT);
        testSnake.rotateLeft();
        assertEquals(Direction.UP, testSnake.getDirection());
    }

    @Test
    public void testUpRotateLeft() {
        setSnakeDirection(testSnake, Direction.UP);
        testSnake.rotateLeft();
        assertEquals(Direction.LEFT, testSnake.getDirection());
    }

    @Test
    public void testDownRotateLeft() {
        setSnakeDirection(testSnake, Direction.DOWN);
        testSnake.rotateLeft();
        assertEquals(Direction.RIGHT, testSnake.getDirection());
    }

    @Test
    public void testLeftRotateRight() {
        setSnakeDirection(testSnake, Direction.LEFT);
        testSnake.rotateRight();
        assertEquals(Direction.UP, testSnake.getDirection());
    }

    @Test
    public void testRightRotateRight() {
        setSnakeDirection(testSnake, Direction.RIGHT);
        testSnake.rotateRight();
        assertEquals(Direction.DOWN, testSnake.getDirection());
    }

    @Test
    public void testUpRotateRight() {
        setSnakeDirection(testSnake, Direction.UP);
        testSnake.rotateRight();
        assertEquals(Direction.RIGHT, testSnake.getDirection());
    }

    @Test
    public void testDownRotateRight() {
        setSnakeDirection(testSnake, Direction.DOWN);
        testSnake.rotateRight();
        assertEquals(Direction.LEFT, testSnake.getDirection());
    }


    // EFFECTS: rotate snake until it is facing in direction d
    private void setSnakeDirection(Snake snake, Direction d) {
        while (snake.getDirection() != d)
            snake.rotateLeft();
    }


    // MODIFIES: this
    // EFFECTS:  get snake to eat enough food so that its body has length 2
    private void growBodyByTwo() {
        Food food = new Food(testSnake.getPosition(), 2 * Snake.NUTRITION_TO_GROW);
        testSnake.eat(food);
        testSnake.move();
        testSnake.move();
    }


}