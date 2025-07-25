package com.anishoeffects.plugins.aEJoinMenssager.listener;

import com.anishoeffects.plugins.aEJoinMenssager.AEJoinMenssager;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class JoinListener implements Listener {
    private final AEJoinMenssager plugin;

    public JoinListener(AEJoinMenssager plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoinListener(PlayerJoinEvent event) {
        // Pegando o Player do evento.
        Player player = event.getPlayer();
        // Criando mensagem de Boas Vindas.
        final Component textWelcome = plugin.getChatMessage("chat-text", player);
        // Adicionando Mensagem ao Evento
        event.joinMessage(textWelcome);
        WelcomeTitle(player, player);
    }

    public void WelcomeTitle(@NotNull Audience target, Player player) {
        // Criando um componente.
        Component mainTitle = plugin.getTitleMessage("welcome-title-main", player);
        Component subtitle = plugin.getTitleMessage("welcome-title-subtitle", player);

        // Criando o titulo
        final Title title = Title.title(mainTitle, subtitle);

        // Mostrando o titulo na tela
        target.showTitle(title);

}


}
