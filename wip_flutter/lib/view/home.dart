import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../database/wip_db.dart';
import 'wip_menu.dart';

class Home extends StatefulWidget {
  const Home({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<Home> createState() => _HomeState();
}

class _HomeState extends State<Home> {

  String imagesPath = 'assets/images/home/';

  String playButtonPath = 'assets/images/home/play_button.png';

  late Image playButtonPressed;

  @override
  void initState() {
    super.initState();
    playButtonPressed = Image.asset('${imagesPath}play_button_pressed.png');
    getDatabase();
    setUserSharedPreferences();
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    precacheImage(playButtonPressed.image, context);
  }

  void setPlayButtonPath(String playButtonPath) {
    setState(() {
      this.playButtonPath = imagesPath + playButtonPath;
    });
  }

  //Per popolare il database al primo avvio dell'app
  void getDatabase() async {
    await WIPDb.getDb();
  }

  void setUserSharedPreferences() async {

    final sharedPreferences = await SharedPreferences.getInstance();

    sharedPreferences.setInt('userId', 1);

  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
          body: Container(
            decoration: const BoxDecoration(
                image: DecorationImage(
                    image: AssetImage('assets/images/shared/background.png'),
                    fit: BoxFit.fill
                )
            ),
            padding: const EdgeInsets.only(top: 70, bottom: 30),
            child: Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Column(
                    children: [
                      Transform(
                          transform: Matrix4.rotationZ(-0.25),
                          child: Image.asset('assets/images/home/app_name.png')
                      ),
                      Container(
                        margin: const EdgeInsets.only(top: 10, left: 35, right: 35),
                        child: Image.asset('assets/images/home/under_app_name.png'),
                      )
                    ],
                  ),
                  Container(
                      decoration: const BoxDecoration(
                          image: DecorationImage(
                              image: AssetImage('assets/images/shared/frame.png'),
                              fit: BoxFit.fill
                          )
                      ),
                      padding: const EdgeInsets.only(top: 33, bottom: 33, left: 37, right: 37),
                      child: GestureDetector(
                          onTapDown: (_){
                            setPlayButtonPath('play_button_pressed.png');
                          },
                          onPanDown: (_){
                            setPlayButtonPath('play_button_pressed.png');
                          },
                          onTapCancel: (){
                            setPlayButtonPath('play_button.png');
                          },
                          onPanEnd: (_){
                            setPlayButtonPath('play_button.png');
                          },
                          onTap: () {
                            setPlayButtonPath('play_button.png');
                            Navigator.pushNamed(context, '/start-story');
                          },
                          child: Image.asset(playButtonPath),
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