import 'package:flutter/material.dart';
import 'package:sqflite/sqflite.dart';
import 'package:wip_flutter/database/dao/chapter_dao.dart';
import 'package:wip_flutter/database/dao/shop_element_dao.dart';
import 'package:wip_flutter/database/dao/shopped_dao.dart';
import 'package:wip_flutter/database/dao/story_dao.dart';
import 'package:wip_flutter/database/dao/user_dao.dart';
import 'package:wip_flutter/database/model/shop_element.dart';
import 'package:wip_flutter/database/model/shopped.dart';
import 'package:wip_flutter/database/wip_db.dart';

import '../database/model/chapter.dart';
import '../database/model/story.dart';
import '../database/model/user.dart';


class Home extends StatefulWidget {
  const Home({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<Home> createState() => _HomeState();

}

class _HomeState extends State<Home> {

  late Database wipDb;

  String imagesPath = 'assets/images/';

  String playButtonPath = 'assets/images/play_button.png';
  String homeButtonPath = 'assets/images/paint_button.png';

  late Image playButtonPressed;
  late Image homeButtonPressed;

  @override
  void initState() {
    super.initState();
    playButtonPressed = Image.asset('${imagesPath}play_button_pressed.png');
    homeButtonPressed = Image.asset('${imagesPath}paint_button_pressed.png');
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    precacheImage(playButtonPressed.image, context);
    precacheImage(homeButtonPressed.image, context);
  }

  void setPlayButtonPath(String playButtonPath) {
    setState(() {
      this.playButtonPath = imagesPath + playButtonPath;
    });
  }

  void setHomeButtonPath(String homeButtonPath) {
    setState(() {
      this.homeButtonPath = imagesPath + homeButtonPath;
    });
  }

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
            padding: const EdgeInsets.only(top: 70, bottom: 30),
            child: Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Column(
                    children: [
                      Transform(
                          transform: Matrix4.rotationZ(-0.25),
                          child: Image.asset('assets/images/app_name.png')
                      ),
                      Container(
                        margin: const EdgeInsets.only(top: 10, left: 35, right: 35),
                        child: Image.asset('assets/images/under_app_name.png'),
                      )
                    ],
                  ),
                  Container(
                      decoration: const BoxDecoration(
                          image: DecorationImage(
                              image: AssetImage('assets/images/frame.png'),
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
                            Navigator.pushNamed(context, '/start-story');
                            setPlayButtonPath('play_button.png');
                          },
                          child: Image.asset(playButtonPath),
                      )
                  ),
                  GestureDetector(
                      onTapDown: (_){
                        setHomeButtonPath('paint_button_pressed.png');
                      },
                      onPanDown: (_){
                        setHomeButtonPath('paint_button_pressed.png');
                      },
                      onTapCancel: (){
                        setHomeButtonPath('paint_button.png');
                      },
                      onPanEnd: (_){
                        setHomeButtonPath('paint_button.png');
                      },
                      onTap: (){
                        setHomeButtonPath('paint_button.png');
                      },
                      child: Image.asset(homeButtonPath)
                  )
                ],
              ),
            ),
          ),
    );
  }
}