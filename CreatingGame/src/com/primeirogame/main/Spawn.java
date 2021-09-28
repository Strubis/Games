package com.primeirogame.main;

import java.util.Random;

/**
 *
 * @author Emerson
 */
public class Spawn {

    private Handler handler;
    private HUD hud;
    private Random r;
    private Game game;

    private int scoreKeep = 0;

    public Spawn(Handler handler, HUD hud, Game game) {
        this.handler = handler;
        this.hud = hud;
        this.game = game;

        r = new Random();
    }

    public void tick() {
        scoreKeep++;

        if (scoreKeep >= 350) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

            if (Game.diff == 0) {
                if (hud.getLevel() == 2) {
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),
                            r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
                } else if (hud.getLevel() == 3) {
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50),
                            r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
                } else if (hud.getLevel() == 5) {
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),
                            r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
                } else if (hud.getLevel() == 7) {
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),
                            r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                } else if (hud.getLevel() == 9) {
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50),
                            r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
                } else if (hud.getLevel() == 10) {
                    handler.clearEnemys();
                    handler.addObject( new EnemyBoss( ( Game.WIDTH / 2 ) - 40, 
                            -120, ID.EnemyBoss, handler ) );
                }
            } else if (Game.diff == 1) {
                if (hud.getLevel() == 2) {
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50),
                            r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
                } else if (hud.getLevel() == 3) {
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50),
                            r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
                } else if (hud.getLevel() == 5) {
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50),
                            r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
                } else if (hud.getLevel() == 6) {
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),
                            r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                } else if (hud.getLevel() == 8) {
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50),
                            r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                } else if (hud.getLevel() == 9) {
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50),
                            r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
                } else if (hud.getLevel() == 10) {
                    handler.clearEnemys();
                    handler.addObject( new EnemyBoss( ( Game.WIDTH / 2 ) - 40, 
                            -120, ID.EnemyBoss, handler ) );
                }
            }
        }
    }
}
