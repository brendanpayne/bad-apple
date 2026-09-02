package com.thememecult.fwen;

/**
 * Shared facts about the Bad Apple!! asset, needed on both sides because the
 * effect duration is derived from the frame count.
 */
public final class BadApple {
    private BadApple() {}

    /** Frames exported from the source video. */
    public static final int FRAME_COUNT = 3286;
    /** Export frame rate. */
    public static final int FPS = 15;

    /** Atlas cell size, and the source video's 4:3 aspect. */
    public static final int CELL_WIDTH = 64;
    public static final int CELL_HEIGHT = 48;
    /** Cells per atlas row. */
    public static final int COLUMNS = 64;

    public static final int ATLAS_WIDTH = 4096;
    public static final int ATLAS_HEIGHT = 2496;

    /**
     * 3286 frames at 15fps is 219.067s. Rounded up so the effect always outlives
     * the final frame rather than cutting it off.
     */
    public static final int DURATION_TICKS =
            (int) Math.ceil(FRAME_COUNT * 20.0 / FPS);
}
