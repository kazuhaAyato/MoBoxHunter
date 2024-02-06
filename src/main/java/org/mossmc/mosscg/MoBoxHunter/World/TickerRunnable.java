package org.mossmc.mosscg.MoBoxHunter.World;

import com.alonsoaliaga.alonsolevels.api.AlonsoLevelsAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.mossmc.mosscg.MoBoxCore.Game.GameBasicInfo;
import org.mossmc.mosscg.MoBoxCore.Game.GameStatus;
import org.mossmc.mosscg.MoBoxHunter.BasicInfo;
import org.mossmc.mosscg.MoBoxHunter.Main;
import org.mossmc.mosscg.MoBoxHunter.Player.PlayerCache;
import org.mossmc.mosscg.MoBoxHunter.Player.PlayerPoint;
import org.mossmc.mosscg.MoBoxHunter.Step.StepEnding;

import java.util.Objects;

public class TickerRunnable implements Runnable {
    @Override
    public void run() {
        BasicInfo.timer ++;
        if(BasicInfo.timer == 60){
            PlayerPoint.playerCompleteList.forEach(uuid -> {
                AlonsoLevelsAPI.addExperience(uuid,2);
            });
            BasicInfo.timer = 0;
        }
        if (!BasicInfo.isFastMode) return;
        if (GameBasicInfo.gameStatus == GameStatus.gameStatus.Ending) return;
        if ((System.currentTimeMillis() - BasicInfo.StartTime) >= Main.getConfig.getInt("time-mode") * 60000L) {
            BasicInfo.winner = BasicInfo.playerRole.Runner;
            BasicInfo.endLocation = Objects.requireNonNull(Bukkit.getPlayer(PlayerCache.runnerList.get(0))).getLocation();
            Bukkit.broadcastMessage(ChatColor.GREEN + "逃亡者存活了20分钟！游戏胜利！");
            StepEnding.runStep();

        }
    }
}
