package com.crio.jukebox.appConfig;

import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.commands.CreatePlaylistCommand;
import com.crio.jukebox.commands.CreateUserCommand;
import com.crio.jukebox.commands.DeletePlaylistCommand;
import com.crio.jukebox.commands.ICommand;
import com.crio.jukebox.commands.ModifyPlaylistCommand;
import com.crio.jukebox.commands.PlayPlaylistCommand;
import com.crio.jukebox.commands.PlaySongCommand;
import com.crio.jukebox.repositories.PlayListRepositoryImpl;
import com.crio.jukebox.repositories.PlaylistRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;
import com.crio.jukebox.repositories.UserRepositoryImpl;
import com.crio.jukebox.repositories.dataFolder.DataImpl;
import com.crio.jukebox.repositories.dataFolder.LoadData;
import com.crio.jukebox.services.PlayListService;
import com.crio.jukebox.services.PlayServiceImpl;
import com.crio.jukebox.services.SongService;
import com.crio.jukebox.services.SongServiceImpl;
import com.crio.jukebox.services.UserService;
import com.crio.jukebox.services.UserServiceImpl;
import com.crio.jukebox.repositories.SongRepositoryImpl;

public class ApplicationConfig {
    UserRepository userRepository=new UserRepositoryImpl();
    PlaylistRepository playlistRepository=new PlayListRepositoryImpl();
    SongRepository songRepository=new SongRepositoryImpl();
    // UserService userService=new UserRepositoryImpl(userRepository);
    PlayListService playListService =new PlayServiceImpl(playlistRepository, songRepository, userRepository);
    SongService songService=new SongServiceImpl(songRepository,userRepository);
    UserService userService=new UserServiceImpl(userRepository);

    ICommand createUserCommand=new CreateUserCommand(userService);
    ICommand createPlaylistCommand = new CreatePlaylistCommand(playListService);
    ICommand deletePlaylistCommand = new DeletePlaylistCommand(playListService);
    ICommand modifyPlaylistCommand = new ModifyPlaylistCommand(playListService);
    ICommand playPlaylistCommand = new PlayPlaylistCommand(playListService);
    ICommand playSongCommand = new PlaySongCommand(songService);
    CommandInvoker commandInvoker = new CommandInvoker();
    LoadData dataLoader = new LoadData();
    public LoadData getDataLoader(){
        dataLoader.register("LOAD-DATA", new DataImpl(songRepository));
        return dataLoader;
    }
    public CommandInvoker getCommandInvoker(){
        commandInvoker.register("CREATE-USER", createUserCommand);
        commandInvoker.register("CREATE-PLAYLIST", createPlaylistCommand);
        commandInvoker.register("MODIFY-PLAYLIST", modifyPlaylistCommand);
        commandInvoker.register("DELETE-PLAYLIST", deletePlaylistCommand);
        commandInvoker.register("PLAY-PLAYLIST", playPlaylistCommand);
        commandInvoker.register("PLAY-SONG", playSongCommand);
        return commandInvoker;
    }
}
