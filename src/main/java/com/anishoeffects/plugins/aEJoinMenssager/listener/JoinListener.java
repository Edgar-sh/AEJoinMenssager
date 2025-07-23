package com.anishoeffects.plugins.aEJoinMenssager.listener;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

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


    }
}
