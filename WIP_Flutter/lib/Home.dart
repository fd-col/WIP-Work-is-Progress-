import 'package:flutter/material.dart';

class Home extends StatefulWidget {
  const Home({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<Home> createState() => _HomeState();

}

class _HomeState extends State<Home> {

  String s = 'assets/images/play_button.png';

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
                      child: GestureDetector(
                          onTapDown: (_){
                            setState(() {s = 'assets/images/play_button_pressed.png';});
                          },
                          onPanDown: (_){
                            setState(() {s = 'assets/images/play_button_pressed.png';});
                          },
                          onPanCancel: (){
                            setState(() {s = 'assets/images/play_button.png';});
                          },
                          onTapCancel: (){
                            setState(() {s = 'assets/images/play_button.png';});
                          },
                          child: IconButton(
                              padding: const EdgeInsets.only(top: 0, bottom: 0, left: 10, right: 10),
                              icon: Image.asset(s),
                              iconSize: 200,
                              onPressed: () {
                                Navigator.pushNamed(context, '/start-story');
                              }
                          )
                      )
                  ),
                  Image.asset('assets/images/paint_button.png')
                ],
              ),
            ),
          ),
    );
  }
}