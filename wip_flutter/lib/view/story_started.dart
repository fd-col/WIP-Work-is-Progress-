import 'dart:async';

import 'package:flutter/material.dart';
import 'package:wip_flutter/arguments/story_started_arguments.dart';
import 'package:wip_flutter/utils/resource_helper.dart';
import 'package:wip_flutter/view/wip_dialog.dart';

import '../arguments/wip_dialog_arguments.dart';

class StoryStarted extends StatefulWidget {
  const StoryStarted({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<StoryStarted> createState() => _StoryStartedState();

}

class _StoryStartedState extends State<StoryStarted> {

  Duration duration = const Duration();
  Timer? timer;

  String minutesText = '00';
  String secondsText = '00';

  String imagesPath = 'assets/images/story-started/';

  String stopButtonPath = 'assets/images/story-started/stop_button.png';

  late Image stopButtonPressed;

  StoryStartedArguments? args;
  int? studyTime;
  int? breakTime;
  int? pauseTime;
  int? maxTime;
  int? step;
  int cyclesFinished = 0;

  String avatarPath = '';
  String backgroundPath = '';

  int backgroundEvolution = 0;

  String phrase = 'Dai tutto te stesso';

  @override
  void initState() {
    super.initState();
    stopButtonPressed = Image.asset('${imagesPath}stop_button_pressed.png');
    startTimer();
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    precacheImage(stopButtonPressed.image, context);
  }

  void setStopButtonPath(String stopButtonPath) {
    setState(() {
      this.stopButtonPath = imagesPath + stopButtonPath;
    });
  }

  void startTimer() {
    timer = Timer.periodic(const Duration(seconds: 1), (_) => addTime());
  }

  String twoDigits(int n) => n.toString().padLeft(2, '0');

  void addTime() {
    const addSeconds = 400;

    if(mounted) {
      setState(() {
        final seconds = duration.inSeconds + addSeconds;

        duration = Duration(seconds: seconds);

        minutesText = twoDigits(duration.inMinutes);
        secondsText = twoDigits(duration.inSeconds.remainder(60));
      });
    }
  }

  void setInitialBackground(String backgroundName) {
    String backgroundPath = fromShopElementBackgroundToPath(backgroundName);
    this.backgroundPath = backgroundPath;
  }

  void setEvolutedBackground(String backgroundName) {
    String backgroundPath = fromShopElementEvolutedBackgroundToPath(backgroundName, backgroundEvolution);
    this.backgroundPath = backgroundPath;
  }

  bool isBetween(int value, int start, int end) {
    return start <= value && value < end;
  }

  int getCurrentCycleSeconds() {
    return duration.inSeconds - (maxTime! * cyclesFinished);
  }

  @override
  Widget build(BuildContext context) {

    args ??= ModalRoute
          .of(context)!
          .settings
          .arguments as StoryStartedArguments;

    studyTime ??= args!.studyTime * 60;
    breakTime ??= args!.breakTime * 60;
    pauseTime ??= studyTime;
    maxTime ??= studyTime! + breakTime!;
    step ??= studyTime!~/4;

    //args = StoryStartedArguments(storyTitle: 'sas', studyTime: 12, breakTime: 23, selectedAvatar: 'venere', background: 'munch_background');

    if(backgroundEvolution == 0 && isBetween(getCurrentCycleSeconds(), 0, step!)) {
      args?.backgroundNames.shuffle();
      backgroundEvolution++;
      avatarPath = fromShopElementAvatarToPath(args!.selectedAvatar);
      setInitialBackground(args!.backgroundNames.first);
      phrase = 'Dai tutto te stesso';
    } else if(backgroundEvolution == 1 && isBetween(getCurrentCycleSeconds(), step!, step!*2)) {
      backgroundEvolution++;
      setEvolutedBackground(args!.backgroundNames.first);
    } else if(backgroundEvolution == 2 && isBetween(getCurrentCycleSeconds(), step!*2, step!*3)) {
      backgroundEvolution++;
      setEvolutedBackground(args!.backgroundNames.first);
    } else if(backgroundEvolution == 3 && isBetween(getCurrentCycleSeconds(), step!*3, pauseTime!)) {
      backgroundEvolution++;
      setEvolutedBackground(args!.backgroundNames.first);
    } else if(backgroundEvolution == 4 && isBetween(getCurrentCycleSeconds(), pauseTime!, maxTime!)) {
      avatarPath = 'assets/images/story-started/bonfire.png';
      phrase = 'Prenditi una pausa';
    } else if(getCurrentCycleSeconds() >= maxTime!) {
      backgroundEvolution = 0;
      cyclesFinished++;
    }

    return Scaffold(
          body: Container(
            decoration: const BoxDecoration(
                image: DecorationImage(
                    image: AssetImage('assets/images/shared/background.png'),
                    fit: BoxFit.fill
                )
            ),
            child: Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Container(
                      height: 170,
                      padding: const EdgeInsets.only(left: 20, right: 20),
                      decoration: const BoxDecoration(
                            image: DecorationImage(
                                image: AssetImage('assets/images/story-started/background_control_panel_story_started_up.png'),
                                fit: BoxFit.fill
                            ),
                      ),
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceAround,
                        children: [
                          Image.asset(avatarPath),
                          Flexible(
                              child: Text(
                                phrase,
                                textAlign: TextAlign.center,
                                style: const TextStyle(
                                    color: Color.fromARGB(255, 2, 119, 189),
                                    fontFamily: 'PressStart2P',
                                    fontSize: 22
                                ),
                              )
                          )
                        ]
                      )
                    ),
                    Container(
                      height: 260,
                      padding: const EdgeInsets.only(left: 8, bottom: 32),
                      decoration: const BoxDecoration(
                        image: DecorationImage(
                          image: AssetImage('assets/images/story-started/story_stand.png')
                        )
                      ),
                      child: Image.asset(backgroundPath),
                    ),
                    Container(
                      height: 110,
                      margin: const EdgeInsets.only(top: 40),
                      decoration: const BoxDecoration(
                        image: DecorationImage(
                            image: AssetImage('assets/images/story-started/background_control_panel_story_started_down.png'),
                          fit: BoxFit.fill
                        )
                      ),
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceAround,
                        children: [
                          GestureDetector(
                              onTapDown: (_){
                                setStopButtonPath('stop_button_pressed.png');
                              },
                              onPanDown: (_){
                                setStopButtonPath('stop_button_pressed.png');
                              },
                              onTapCancel: (){
                                setStopButtonPath('stop_button.png');
                              },
                              onPanEnd: (_){
                                setStopButtonPath('stop_button.png');
                              },
                              onTap: () {
                                timer!.cancel();
                                showDialog(barrierDismissible: false, context: context, builder: (context) {

                                  List<String> otherText = <String>[];

                                  otherText.add('Ottimo lavoro!');

                                  otherText.add('Il tuo sforzo\nverrà ripagato');

                                  List<Widget> children = makeChildrenSection('Giorno di\npaga', '"Cha Ching"', otherText, true);

                                  var args = WIPDialogArguments(
                                      children: children,
                                      dialogHeight: 300,
                                      popUntilRoot: true
                                  );

                                  return WIPDialog(args: args);

                                });
                                setStopButtonPath('stop_button.png');
                              },
                              child: Image.asset(stopButtonPath)
                          ),
                          Text(
                            '$minutesText:$secondsText',
                            textAlign: TextAlign.center,
                            style: const TextStyle(
                                color: Colors.white,
                                fontFamily: 'PressStart2P',
                                fontSize: 30
                            ),
                          )
                        ],
                      ),
                    )
                  ]
              ),
            ),
          ),
    );
  }
}