import 'dart:async';

import 'package:flutter/material.dart';
import 'package:wip_flutter/arguments/story_started_arguments.dart';
import 'package:wip_flutter/utils/resource_helper.dart';

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

  String imagesPath = 'assets/images/';

  String stopButtonPath = 'assets/images/stop_button.png';

  late Image stopButtonPressed;

  StoryStartedArguments? args;

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
    const addSeconds = 1;

    if(mounted) {
      setState(() {
        final seconds = duration.inSeconds + addSeconds;

        duration = Duration(seconds: seconds);

        minutesText = twoDigits(duration.inMinutes);
        secondsText = twoDigits(duration.inSeconds.remainder(60));
      });
    }
  }

  @override
  Widget build(BuildContext context) {

    args ??= ModalRoute
          .of(context)!
          .settings
          .arguments as StoryStartedArguments;

    return Scaffold(
          body: Container(
            decoration: const BoxDecoration(
                image: DecorationImage(
                    image: AssetImage('assets/images/background.png'),
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
                                image: AssetImage('assets/images/background_control_panel_story_started_up.png'),
                                fit: BoxFit.fill
                            ),
                      ),
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceAround,
                        children: [
                          Image.asset(fromShopElementToPath(args!.selectedAvatar)),
                          const Flexible(
                              child: Text(
                                'Dai tutto te stesso',
                                textAlign: TextAlign.center,
                                style: TextStyle(
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
                          image: AssetImage('assets/images/story_stand.png')
                        )
                      ),
                      child: Image.asset('assets/images/dali_1.png'),
                    ),
                    Container(
                      height: 110,
                      margin: const EdgeInsets.only(top: 40),
                      decoration: const BoxDecoration(
                        image: DecorationImage(
                            image: AssetImage('assets/images/background_control_panel_story_started_down.png'),
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
                                Navigator.popUntil(context, ModalRoute.withName('/'));
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