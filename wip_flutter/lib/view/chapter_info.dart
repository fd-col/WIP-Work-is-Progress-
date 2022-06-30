import 'package:flutter/material.dart';
import 'package:wip_flutter/arguments/chapter_info_arguments.dart';
import 'package:wip_flutter/utils/resource_helper.dart';
import 'package:wip_flutter/view/wip_menu.dart';

class ChapterInfo extends StatefulWidget {
  const ChapterInfo({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<ChapterInfo> createState() => _ChapterInfoState();

}

class _ChapterInfoState extends State<ChapterInfo> {
  
  ChapterInfoArguments? args;
  
  final double _maxSlider = 60;

  bool silentMode = false;
  bool hardcoreMode = false;
  
  String imagesPath = 'assets/images/shared/';

  String backButtonPath = 'assets/images/shared/back_button.png';

  late Image backButtonPressed;

  @override
  void initState() {
    super.initState();
    backButtonPressed = Image.asset('${imagesPath}back_button_pressed.png');
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    precacheImage(backButtonPressed.image, context);
  }

  void setBackButtonPath(String backButtonPath) {
    setState(() {
      this.backButtonPath = imagesPath + backButtonPath;
    });
  }

  @override
  Widget build(BuildContext context) {

    args ??= ModalRoute
        .of(context)!
        .settings
        .arguments as ChapterInfoArguments;
    
    switch(args!.mode) {
      case 'silent':
        silentMode = true;
        break;
      case 'hardcore':
        silentMode = true;
        hardcoreMode = true;
    }
    
    return Scaffold(
      body: Container(
        decoration: const BoxDecoration(
            image: DecorationImage(
                image: AssetImage('assets/images/shared/background.png'),
                fit: BoxFit.fill
            )
        ),
        padding: const EdgeInsets.only(top: 50, bottom: 30),
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Column(
                children: [
                  Container(
                      width: MediaQuery.of(context).size.width - 10,
                      padding: const EdgeInsets.only(left: 5, right: 5),
                      child: Wrap(
                        runAlignment: WrapAlignment.start,
                        spacing: MediaQuery.of(context).size.width * 0.1,
                        children: [
                          GestureDetector(
                              onTapDown: (_){
                                setBackButtonPath('back_button_pressed.png');
                              },
                              onPanDown: (_){
                                setBackButtonPath('back_button_pressed.png');
                              },
                              onTapCancel: (){
                                setBackButtonPath('back_button.png');
                              },
                              onPanEnd: (_){
                                setBackButtonPath('back_button.png');
                              },
                              onTap: () {
                                Navigator.pop(context);
                                setBackButtonPath('back_button.png');
                              },
                              child: Image.asset(backButtonPath, height: 60, width: 60, fit: BoxFit.cover)
                          ),
                          const Text(
                            'Info\nCapitolo',
                            textAlign: TextAlign.center,
                            style: TextStyle(
                              color: Colors.white,
                              fontFamily: 'PressStart2P',
                              fontSize: 26,
                            ),
                          ),
                        ],
                      )
                  ),
                  Container(
                    margin: const EdgeInsets.only(top: 10, left: 35, right: 35),
                    child: Image.asset('assets/images/home/under_app_name.png'),
                  )
                ],
              ),
              Container(
                height: MediaQuery.of(context).size.height * 0.65,
                padding: const EdgeInsets.only(left: 25, right: 25),
                child: ListView(
                  padding: EdgeInsets.zero,
                  children: [
                    Wrap(
                      alignment: WrapAlignment.center,
                      runSpacing: 20,
                      children: [
                        const Text(
                          'Data:',
                          textAlign: TextAlign.center,
                          style: TextStyle(
                            color: Colors.white,
                            fontFamily: 'PressStart2P',
                            fontSize: 20,
                          ),
                        ),
                        Container(
                          height: 70,
                          decoration: const BoxDecoration(
                              image: DecorationImage(
                                image: AssetImage('assets/images/shared/rectangular_background.png'),
                              )
                          ),
                          padding: const EdgeInsets.only(left: 20, right: 20),
                          child: Center(
                              child: Text(
                                  args!.createdOn,
                                  textAlign: TextAlign.center,
                                  style: const TextStyle(
                                    color: Color.fromARGB(255, 2, 119, 189),
                                    fontFamily: 'PressStart2P',
                                    fontSize: 16,
                                  )
                              )
                          ),
                        ),
                        const Text(
                          'Tempo:',
                          textAlign: TextAlign.center,
                          style: TextStyle(
                            color: Colors.white,
                            fontFamily: 'PressStart2P',
                            fontSize: 20,
                          ),
                        ),
                        Container(
                          height: 70,
                          decoration: const BoxDecoration(
                              image: DecorationImage(
                                image: AssetImage('assets/images/shared/rectangular_background.png'),
                              )
                          ),
                          padding: const EdgeInsets.only(left: 20, right: 20),
                          child: Center(
                              child: Text(
                                  args!.time,
                                  textAlign: TextAlign.center,
                                  style: const TextStyle(
                                    color: Color.fromARGB(255, 2, 119, 189),
                                    fontFamily: 'PressStart2P',
                                    fontSize: 16,
                                  )
                              )
                          ),
                        ),
                        const Text(
                          'Lavoro/Pausa:',
                          textAlign: TextAlign.center,
                          style: TextStyle(
                            color: Colors.white,
                            fontFamily: 'PressStart2P',
                            fontSize: 20,
                          ),
                        ),
                        Container(
                          height: 70,
                          decoration: const BoxDecoration(
                              image: DecorationImage(
                                  image: AssetImage('assets/images/shared/rectangular_background.png'),
                                  fit: BoxFit.fill
                              )
                          ),
                          child: Center(
                              child: Slider(
                                thumbColor: const Color.fromARGB(255, 2, 119, 189),
                                min: 10.0,
                                max: _maxSlider - 10,
                                divisions: _maxSlider~/10 - 2, //Numero di divisioni - 1 sinistra e -1 destra
                                label: '${args!.studyTime.round()} min lavoro/${args!.breakTime.round()} min pausa',
                                value: args!.studyTime.toDouble(),
                                onChanged: (newStudyTime) {
                                  setState(() {
                                  });
                                },
                              )
                          ),
                        ),
                        Container(
                          height: 110,
                          decoration: const BoxDecoration(
                              image: DecorationImage(
                                  image: AssetImage('assets/images/shared/rectangular_background.png'),
                                  fit: BoxFit.fill
                              )
                          ),
                          //padding: const EdgeInsets.only(left: 20),
                          child: Column(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              Row(
                                  mainAxisAlignment: MainAxisAlignment.center,
                                  children: [
                                    Text(
                                      'Mod. silenzio',
                                      style: TextStyle(
                                        color: const Color.fromARGB(255, 2, 119, 189),
                                        fontFamily: 'PressStart2P',
                                        fontSize: MediaQuery.of(context).size.width * 0.041,
                                      ),
                                    ),
                                    Switch(
                                        value: silentMode,
                                        onChanged: (value) {
                                        }
                                    )
                                  ]
                              ),
                              Row(
                                mainAxisAlignment: MainAxisAlignment.center,
                                children: [
                                  Text(
                                    'Mod. hardcore',
                                    style: TextStyle(
                                      color: const Color.fromARGB(255, 2, 119, 189),
                                      fontFamily: 'PressStart2P',
                                      fontSize: MediaQuery.of(context).size.width * 0.041,
                                    ),
                                  ),
                                  Switch(
                                      value: hardcoreMode,
                                      onChanged: (value) {
                                      }
                                  )
                                ],
                              )
                            ],
                          ),
                        ),
                        Wrap(
                          direction: Axis.vertical,
                          spacing: 20,
                          crossAxisAlignment: WrapCrossAlignment.center,
                          children: [
                            const Text(
                              'Avatar',
                              textAlign: TextAlign.center,
                              style: TextStyle(
                                color: Colors.white,
                                fontFamily: 'PressStart2P',
                                fontSize: 20,
                              ),
                            ),
                            SizedBox(
                                height: 100,
                                width: 60,
                                child: FittedBox(
                                  fit: BoxFit.cover,
                                  child: Image.asset(fromShopElementAvatarToPath(args!.avatar)),
                                )
                            )
                          ],
                        )
                      ]
                    )
                  ],
                )
              ),
              WIPMenu(parentWidget: widget.toString())
            ],
          ),
        ),
      ),
    );
  }
}