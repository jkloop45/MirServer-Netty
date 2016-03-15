import com.zhaoxiaodan.mirserver.db.entities.Player
import com.zhaoxiaodan.mirserver.db.types.Gender
import com.zhaoxiaodan.mirserver.db.types.Job
import com.zhaoxiaodan.mirserver.db.types.MapPoint
import com.zhaoxiaodan.mirserver.gameserver.engine.ItemEngine

void onCreate(Player c) {
    setBaseAbility(c);
    giveItems(c);
}

void setBaseAbility(Player player) {
    player.levelUp(1);

    player.baseAbility.HP = 100;
    player.baseAbility.MaxHP = 100;
    player.baseAbility.MP = 10;
    player.baseAbility.MaxMP = 10;

    player.baseAbility.AC = 1;
    player.baseAbility.AC2 = 1;
    player.baseAbility.MAC = 1;
    player.baseAbility.MAC2 = 1;
    player.baseAbility.DC = 1;
    player.baseAbility.DC2 = 1;
    player.baseAbility.MC = 1;
    player.baseAbility.MC2 = 1;
    player.baseAbility.SC = 1;
    player.baseAbility.SC2 = 1;

    player.MaxExp = getMaxExp(player.Level + 1);

    player.gold = 10000;
    player.gameGold = 1234;
    player.gamePoint = 5678;

    player.homeMapPoint = getStartPoint();        //设置出生点
}

void onLevelUp(Player player) {
    player.MaxExp = getMaxExp(player.Level + 1);

    switch (player.job) {
        case Job.Warrior:
            player.maxHp = 100 + 50 * player.Level;
            player.maxMp = 10 + 10 * player.Level;
            break;
    }
}

int getMaxExp(int nextLevel) {
    return 300 * nextLevel;
}

MapPoint getStartPoint() {
    MapPoint startPoint = new MapPoint();
    startPoint.mapId = "0";
    startPoint.x = 273;
    startPoint.y = 590;

    return startPoint;
}

void giveItems(Player c) {

    c.takeNewItem(ItemEngine.getStdItemByName("金创药(小)包"));
    if (c.job == Job.Warrior) {
        c.takeNewItem(ItemEngine.getStdItemByName("裁决之杖"));
        c.takeNewItem(ItemEngine.getStdItemByName("圣战头盔"));
        c.takeNewItem(ItemEngine.getStdItemByName("圣战项链"));
        c.takeNewItem(ItemEngine.getStdItemByName("圣战手镯"));
        c.takeNewItem(ItemEngine.getStdItemByName("圣战戒指"));
    }
    if (c.gender == Gender.MALE) {
        c.takeNewItem(ItemEngine.getStdItemByName("天魔神甲"));
    } else {
        c.takeNewItem(ItemEngine.getStdItemByName("圣战宝甲"));
    }
}
