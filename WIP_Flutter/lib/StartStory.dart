import 'package:flutter/material.dart';

class StartStory extends StatefulWidget {
  const StartStory({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<StartStory> createState() => _StartStoryState();

}

class _StartStoryState extends State<StartStory> {
  double _studyTime = 20;
  double _pause = 40;
  final double _maxSlider = 120;
  bool _silenceMode = false;
  bool _hardcoreMode = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
          body: Container(
            decoration: const BoxDecoration(
                image: DecorationImage(
                    image: AssetImage('assets/images/background.png'),
                    fit: BoxFit.fill
                )
            ),
            padding: const EdgeInsets.only(top: 30, bottom: 30, left: 20, right: 20),
            child: Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  Align(
                    alignment: Alignment.centerLeft,
                    child: GestureDetector(
                      onTap: () {
                        Navigator.pop(context);
                      },
                      child: Image.asset('assets/images/back_button.png', height: 60, width: 60, fit: BoxFit.cover)
                    )
                  ),
                  const Text(
                    'Titolo',
                    textAlign: TextAlign.center,
                    style: TextStyle(
                        color: Colors.white,
                        fontFamily: 'PressStart2P',
                        fontSize: 22
                    ),
                  ),
                  Container(
                    height: 70,
                    decoration: const BoxDecoration(
                      image: DecorationImage(
                        image: AssetImage('assets/images/rectangular_background.png'),
                        fit: BoxFit.fill
                      )
                    ),
                    padding: const EdgeInsets.only(left: 20, right: 20),
                    child: const Center(
                        child: TextField(
                          decoration: InputDecoration(
                              border: InputBorder.none,
                              hintText: 'Nuova storia',
                              hintStyle: TextStyle(
                                  color: Color.fromARGB(255, 2, 119, 189),
                                  fontFamily: 'PressStart2P',
                                  fontSize: 16
                              )
                          ),
                          style: TextStyle(
                              color: Color.fromARGB(255, 2, 119, 189),
                              fontFamily: 'PressStart2P',
                              fontSize: 16
                          )
                        )
                    ),
                  ),
                  const Text(
                    'Tempo della storia',
                    textAlign: TextAlign.center,
                    style: TextStyle(
                        color: Colors.white,
                        fontFamily: 'PressStart2P',
                        fontSize: 22
                    ),
                  ),
                  Container(
                    height: 70,
                    decoration: const BoxDecoration(
                        image: DecorationImage(
                            image: AssetImage('assets/images/rectangular_background.png'),
                            fit: BoxFit.fill
                        )
                    ),
                    child: Center(
                        child: Slider(
                          thumbColor: const Color.fromARGB(255, 2, 119, 189),
                          min: 10.0,
                          max: _maxSlider - 10,
                          divisions: _maxSlider~/10 - 2, //Numero di divisioni - 1 sinistra e -1 destra
                          label: '${_studyTime.round()} min lavoro/${_pause.round()} min pausa',
                          value: _studyTime,
                          onChanged: (newStudyTime) {
                            setState(() {
                              _studyTime = newStudyTime;
                              _pause = _maxSlider - _studyTime;
                            });
                          },
                        )
                    ),
                  ),
                  const Text(
                    'Impost. storia',
                    textAlign: TextAlign.center,
                    style: TextStyle(
                        color: Colors.white,
                        fontFamily: 'PressStart2P',
                        fontSize: 22
                    ),
                  ),
                  Container(
                    height: 110,
                    decoration: const BoxDecoration(
                        image: DecorationImage(
                            image: AssetImage('assets/images/rectangular_background.png'),
                            fit: BoxFit.fill
                        )
                    ),
                    padding: const EdgeInsets.only(left: 20),
                    child: Row(
                      children: [
                        Column(
                          mainAxisAlignment: MainAxisAlignment.center,
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Row(
                              children: [const Text(
                                'Mod. silenzio',
                                style: TextStyle(
                                    color: Color.fromARGB(255, 2, 119, 189),
                                    fontFamily: 'PressStart2P',
                                    fontSize: 16,
                                ),
                              ),
                                Switch(
                                    value: _silenceMode,
                                    onChanged: (value) {
                                      setState((){
                                            if(!_hardcoreMode) {
                                              _silenceMode = value;
                                            }
                                          }
                                      );
                                    }
                                )
                              ]
                            ),
                            Row(
                              children: [
                                const Text(
                                  'Mod. hardcore',
                                  style: TextStyle(
                                      color: Color.fromARGB(255, 2, 119, 189),
                                      fontFamily: 'PressStart2P',
                                      fontSize: 16,
                                  ),
                                ),
                                Switch(
                                    value: _hardcoreMode,
                                    onChanged: (value) {
                                      setState((){
                                        _silenceMode = value;
                                        _hardcoreMode = value;
                                      }
                                      );
                                    }
                                )
                              ],
                            )
                          ],
                        ),
                        Align(
                          alignment: Alignment.topCenter,
                          child: IconButton(
                            icon: Image.asset('assets/images/info_button.png'),
                            onPressed: () {  },
                          ),
                        )
                      ],
                    )
                  ),
                  const Text(
                    'Scegli avatar',
                    textAlign: TextAlign.center,
                    style: TextStyle(
                        color: Colors.white,
                        fontFamily: 'PressStart2P',
                        fontSize: 22
                    ),
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      IconButton(
                          padding: const EdgeInsets.only(right: 30),
                          icon: Image.asset('assets/images/avatar_sx_arrow.png'),
                          iconSize: 50,
                          onPressed: () {
                          }
                      ),
                      SizedBox(
                          height: 100,
                          width: 60,
                          child: FittedBox(
                              fit: BoxFit.cover,
                              child: Image.asset('assets/images/venere.png'),
                          )
                      ),
                      IconButton(
                          padding: const EdgeInsets.only(left: 30),
                          icon: Image.asset('assets/images/avatar_dx_arrow.png'),
                          iconSize: 50,
                          onPressed: () {
                        }
                      ),
                    ],
                  ),
                  GestureDetector(
                    onTap: () {
                      Navigator.pushNamed(context, '/story-started');
                    },
                    child: Image.asset('assets/images/start_story_button.png')
                  )

                ]
              ),
            ),
          ),
      resizeToAvoidBottomInset: false,
    );
  }
}