# Wall Climb

> A lightweight, client-side mod that turns annoying one-block ledges and two-block steps into climbable terrain — while leaving real walls completely untouched.

**Wall Climb** is a small quality-of-life mod for Minecraft. Face a wall and hold the jump key: your character sticks to the wall, slides straight up, and steps onto the top. No items, no durability, no server-side installation.

## Features ✨

- **Hold jump to climb** — Face a wall and hold the jump key; you cling to the wall and climb straight up to its top.
- **Configurable height** — The maximum climbable wall height is configurable (default 2 blocks), so real walls, cliffs, and buildings remain unaffected unless you raise the limit.
- **Smooth dismount** — A brief mounting animation plays as you reach the top, letting you step naturally onto the edge.
- **No item required** — Climb with empty hands. Nothing is consumed, and no durability is lost.
- **Identical in survival and creative** — Behavior is the same in both game modes.
- **Pure client-side logic** — Singleplayer, LAN, and servers all behave identically; the server needs nothing installed.

## Configuration ⚙️

All options are stored in `config/wallclimb-client.toml` and support hot reload. A configuration screen is also available from the mod list.

| Option | Key | Default | Range | Description |
| --- | --- | --- | --- | --- |
| Maximum Height | `maxHeight` | `2` | `0` – `1000` | Tallest wall, in blocks, that can still be climbed; set to `0` to disable the mod. |
| Climb Speed | `climbSpeed` | `3.0` | `1.0` – `8.0` | Climbing speed in blocks per second; a vanilla ladder is about 4. |

## Technical Details 🔧

- Wall detection only runs while the player is standing on the ground, so jumping, parkour, and combat are never interfered with.
- Climbing is suppressed while flying, fall-flying, riding, swimming, or hanging on ladders/vines.
- Fall distance is reset during climbing and mounting, preventing fall damage when you reach the top.

---

# 贴墙攀爬（Wall Climb）

> 一个轻量级纯客户端模组，把恼人的一格台阶和两格方块堆变成可以翻越的地形——而真正的墙壁则完全不受影响。

**贴墙攀爬**是一个小型生活质量模组。面对墙壁按住跳跃键，角色会贴住墙面，沿墙直线向上滑动，然后站上墙顶。无需任何物品、不消耗耐久，服务端也不需要安装任何东西。

## 功能特性 ✨

- **按住跳跃即可攀爬** — 面对墙壁按住跳跃键，角色会贴着墙面向上爬，直到站上墙顶。
- **可配置攀爬高度** — 最大可爬墙高可通过配置调整（默认 2 格），真正的墙、悬崖和建筑默认不受影响。
- **平滑的翻越动作** — 到达顶部时会触发一段短暂的攀上动画，让你自然地站上边缘。
- **空手即可，无需物品** — 空手即可攀爬，不消耗任何物品，也不损失耐久。
- **生存与创造表现一致** — 两种游戏模式下的行为完全相同。
- **纯客户端逻辑** — 单人、局域网和服务器行为完全一致，服务器无需安装任何内容。

## 配置项 ⚙️

所有选项保存在 `config/wallclimb-client.toml` 中，支持热重载；同时也可以在模组列表中打开配置界面进行修改。

| 选项 | 键名 | 默认值 | 范围 | 说明 |
| --- | --- | --- | --- | --- |
| 最大可爬高度 | `maxHeight` | `2` | `0` – `1000` | 仍然爬得上去的最高墙高（格）；填 `0` 关闭此功能。 |
| 攀爬速度 | `climbSpeed` | `3.0` | `1.0` – `8.0` | 攀爬速度（格/秒），原版梯子约为 4。 |

## 技术细节 🔧

- 只有玩家站在地面上时才会进行墙壁检测，因此跳跃、跑酷和战斗完全不受影响。
- 飞行、鞘翅滑翔、骑乘、游泳以及攀附梯子/藤蔓时，攀爬会被禁用。
- 攀爬与翻越期间会持续重置摔落距离，到达墙顶时不会受到摔落伤害。