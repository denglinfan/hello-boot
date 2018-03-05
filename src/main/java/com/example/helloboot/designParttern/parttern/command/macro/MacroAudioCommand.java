package com.example.helloboot.designParttern.parttern.command.macro;

import com.example.helloboot.designParttern.parttern.command.example.Command;

import java.util.ArrayList;
import java.util.List;

public class MacroAudioCommand implements MacroComand {

    private List<Command> commandList = new ArrayList<Command>();

    public void add(Command cmd) {
        commandList.add(cmd);
    }

    public void remove(Command cmd) {
        commandList.remove(cmd);
    }

    public void execute() {
        for(Command cmd : commandList){
            cmd.execute();
        }
    }
}
