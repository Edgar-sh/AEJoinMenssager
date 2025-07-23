package com.anishoeffects.plugins.aEJoinMenssager.listener;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoinListener(PlayerJoinEvent event) {

        // Pegando o Player do evento.
        Player player = event.getPlayer();
        // Criando mensagem de Boas Vindas.
        final TextComponent textWelcome = Component.text("Bem vindo ").color(TextColor.color(0xf7e1d7))
                .append(Component.text(player.getName(), TextColor.color(0xedafb8))
                        .append(Component.text(" ao Servidor!", TextColor.color(0xf7e1d7))));

        // Adicionando Mensagem ao Evento
        event.joinMessage(textWelcome);

        WelcomeTitle(player, player);
    }

    public void WelcomeTitle(@NotNull Audience target, Player player) {
        // Criando meus componentes  de titulo
        final Component mainTitle = Component.text("Bem vindo ", TextColor.color(0xf7e1d7))
                .append(Component.text(player.getName(), TextColor.color(0xedafb8)));
        final Component subtitle = Component.text("Ao servidor!", TextColor.color(0xf7e1d7));

        // Criando o meu titulo

        final Title title = Title.title(mainTitle, subtitle);

        target.showTitle(title);

    }


}
