package com.anishoeffects.plugins.aEJoinMenssager;

import com.anishoeffects.plugins.aEJoinMenssager.listener.JoinListener;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.json.JSONComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public final class AEJoinMenssager extends JavaPlugin {

    private JsonObject titleMessages;
    private JsonObject chatMessages;

    @Override
    public void onEnable() {
        this.titleMessages = loadFile("title-messages.json");
        this.chatMessages = loadFile("chat-messages.json");

        // Verificando se diretorio (na pasta plugin) já existe, caso não, cria o diretorio.
        if (!getDataFolder().exists()) {
            getLogger().info("Plugin folder not found, creating...");
            if (getDataFolder().mkdirs()){
                getLogger().info("Plugin folder created sucessfuly.");
            }else {
                getLogger().severe("An error occurred while creating the plugin folder");
            }
        }
        getLogger().info("Plugin AEJoinMessage started successfully!");
        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
    }

    private JsonObject loadFile(String fileName) {
        File file = new File(getDataFolder(), fileName);
        // Garantindo que o arquivo existe na pasta plugins
        if (!file.exists()) {
            saveResource(fileName, false);
        }
        try (FileReader reader = new FileReader(file)) {
            return JsonParser.parseReader(reader).getAsJsonObject();
        } catch (IOException | IllegalStateException e) {
            getLogger().severe("Fail to load " + fileName);
            return new JsonObject(); // Retornando objeto vazio para não para a execução.
        }
    }
    public Component getTitleMessage(String key, Player player){
        return getMessageFromObject(this.titleMessages, key, player);
    }

    public Component getChatMessage(String key, Player player){
        return getMessageFromObject(this.chatMessages, key, player);
    }

    // Metodo para evitar repetição de código.
    private Component getMessageFromObject(JsonObject jsonObject, String key, Player player){
        // Retornando componente vazio caso não seja valido.
        if (jsonObject == null || !jsonObject.has(key)) {
            return Component.text("");
        }

        String jsonString = jsonObject.get(key).toString();

        if (player != null) {
             jsonString = jsonString.replace("{player}", player.getName());
        }

        return JSONComponentSerializer.json().deserialize(jsonString);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin AEJoinMessage disabled successfully!");
    }
}
