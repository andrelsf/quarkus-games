-- Table Games
CREATE TABLE IF NOT EXISTS games (
    game_id         VARCHAR(37)     PRIMARY KEY,
    name            VARCHAR(180)    NOT NULL,
    description     VARCHAR(255)    NOT NULL,
    platform        VARCHAR(20)     NOT NULL,
    created_at      TIMESTAMP       NOT NULL DEFAULT LOCALTIMESTAMP,
    updated_at      TIMESTAMP       NOT NULL DEFAULT LOCALTIMESTAMP,
    UNIQUE          (game_id)
);

CREATE INDEX
    idx_tb_games
ON
    games(game_id, name, platform);