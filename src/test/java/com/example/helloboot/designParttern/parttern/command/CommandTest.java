package com.example.helloboot.designParttern.parttern.command;
import com.example.helloboot.baseUnit.BaseUnit;
import com.example.helloboot.designParttern.parttern.command.example.*;
import com.example.helloboot.designParttern.parttern.command.macro.MacroAudioCommand;
import com.example.helloboot.designParttern.parttern.command.macro.MacroComand;
import org.junit.Test;

public class CommandTest extends BaseUnit {

    @Test
    public void test(){
        //创建接受者
        Receiver receiver = new Receiver();

        //创建命令对象，设定他的接受者
        Command command = new ConcreteCommand(receiver);

        //创建请求者，把命令对象设置进去
        Invoker invoker = new Invoker(command);

        //执行方法
        invoker.action();
    }

    @Test
    public void Player(){
        //创建接受者对象
        AudioPlayer audioPlayer = new AudioPlayer();
        //创建命令对象
        com.example.helloboot.designParttern.parttern.command.example.Command playCommand = new PlayCommand(audioPlayer);
        com.example.helloboot.designParttern.parttern.command.example.Command rewindCommand = new RewindCommand(audioPlayer);
        com.example.helloboot.designParttern.parttern.command.example.Command stopCommand = new StopCommand(audioPlayer);

        //创建请求者对象
        Keypad keypad = new Keypad();
        keypad.setPlayCommand(playCommand);
        keypad.setRewindCommand(rewindCommand);
        keypad.setStopCommand(stopCommand);
        //触发命令
        keypad.play();
        keypad.rewind();
        keypad.stop();

        keypad.play();
        keypad.stop();
    }

    @Test
    public void MacroPlayer(){
        //创建接受者对象
        AudioPlayer audioPlayer = new AudioPlayer();
        //创建命令对象
        com.example.helloboot.designParttern.parttern.command.example.Command playCommand = new PlayCommand(audioPlayer);
        com.example.helloboot.designParttern.parttern.command.example.Command rewindCommand = new RewindCommand(audioPlayer);
        com.example.helloboot.designParttern.parttern.command.example.Command stopCommand = new StopCommand(audioPlayer);

        MacroComand marco = new MacroAudioCommand();

        marco.add(playCommand);
        marco.add(rewindCommand);
        marco.add(stopCommand);

        marco.execute();
    }
}
