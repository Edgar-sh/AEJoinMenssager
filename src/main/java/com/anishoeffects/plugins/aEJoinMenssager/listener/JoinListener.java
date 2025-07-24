package com.anishoeffects.plugins.aEJoinMenssager.listener;

import com.anishoeffects.plugins.aEJoinMenssager.AEJoinMenssager;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.json.JSONComponentSerializer;
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
        final TextComponent textWelcome = Component.text("Bem vindo ").color(TextColor.color(0xf7e1d7))
                .append(Component.text(player.getName(), TextColor.color(0xedafb8)));

        // Adicionando Mensagem ao Evento
        event.joinMessage(textWelcome);

        WelcomeTitle(player, player);

    }

    public void WelcomeTitle(@NotNull Audience target, Player player) {
        // Pegando as strings JSON do arquivo carregado
        String mainTitleJson = plugin.getMessage("welcome-title-main").replace("{player}", player.getName());
        String subtitleJson = plugin.getMessage("welcome-title-subtitle");

        // Deserializando as Strings

        final Component mainTitle = JSONComponentSerializer.json().deserialize(mainTitleJson);
        final Component subtitle = JSONComponentSerializer.json().deserialize(subtitleJson);


        // Criando o meu titulo
        final Title title = Title.title(mainTitle, subtitle);

        target.showTitle(title);

    }


}
