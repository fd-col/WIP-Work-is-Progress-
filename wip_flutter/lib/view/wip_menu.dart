import 'package:flutter/material.dart';

class WIPMenu extends StatefulWidget {
  const WIPMenu({Key? key}) : super(key: key);

  @override
  State<WIPMenu> createState() => _WIPMenuState();
}

class _WIPMenuState extends State<WIPMenu> {

  String menuImagesPath = 'assets/images/menu/';

  String homeButtonPath = 'assets/images/menu/home_button.png';
  String kingdomButtonPath = 'assets/images/menu/kingdom_button.png';
  String settingsButtonPath = 'assets/images/menu/settings_button.png';

  late Image homeButtonPressed;
  late Image kingdomButtonPressed;
  late Image settingsButtonPressed;

  @override
  void initState() {
    super.initState();
    homeButtonPressed = Image.asset('${menuImagesPath}home_button_pressed.png');
    kingdomButtonPressed = Image.asset('${menuImagesPath}kingdom_button_pressed.png');
    settingsButtonPressed = Image.asset('${menuImagesPath}settings_button_pressed.png');
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    precacheImage(homeButtonPressed.image, context);
    precacheImage(kingdomButtonPressed.image, context);
    precacheImage(settingsButtonPressed.image, context);
  }

  void setHomeButtonPath(String homeButtonPath) {
    setState(() {
      this.homeButtonPath = menuImagesPath + homeButtonPath;
    });
  }

  void setKingdomButtonPath(String kingdomButtonPath) {
    setState(() {
      this.kingdomButtonPath = menuImagesPath + kingdomButtonPath;
    });
  }

  void setSettingsButtonPath(String settingsButtonPath) {
    setState(() {
      this.settingsButtonPath = menuImagesPath + settingsButtonPath;
    });
  }

  @override
  Widget build(BuildContext context) {
    return SizedBox(
        height: 110,
        child: Wrap(
            direction: Axis.vertical,
            runSpacing: 1,
            children: [
              Column(
                  mainAxisAlignment: MainAxisAlignment.end,
                  children: [
                    GestureDetector(
                        onTapDown: (_){
                          setKingdomButtonPath('kingdom_button_pressed.png');
                        },
                        onPanDown: (_){
                          setKingdomButtonPath('kingdom_button_pressed.png');
                        },
                        onTapCancel: (){
                          setKingdomButtonPath('kingdom_button.png');
                        },
                        onPanEnd: (_){
                          setKingdomButtonPath('kingdom_button.png');
                        },
                        onTap: (){
                          setKingdomButtonPath('kingdom_button.png');
                        },
                        child: Image.asset(kingdomButtonPath)
                    )
                  ]
              ),
              Column(
                  mainAxisAlignment: MainAxisAlignment.start,
                  children: [
                    GestureDetector(
                        onTapDown: (_){
                          setHomeButtonPath('home_button_pressed.png');
                        },
                        onPanDown: (_){
                          setHomeButtonPath('home_button_pressed.png');
                        },
                        onTapCancel: (){
                          setHomeButtonPath('home_button.png');
                        },
                        onPanEnd: (_){
                          setHomeButtonPath('home_button.png');
                        },
                        onTap: (){
                          setHomeButtonPath('home_button.png');
                        },
                        child: Image.asset(homeButtonPath)
                    )
                  ]
              ),
              Column(
                  mainAxisAlignment: MainAxisAlignment.end,
                  children: [
                    GestureDetector(
                        onTapDown: (_){
                          setSettingsButtonPath('settings_button_pressed.png');
                        },
                        onPanDown: (_){
                          setSettingsButtonPath('settings_button_pressed.png');
                        },
                        onTapCancel: (){
                          setSettingsButtonPath('settings_button.png');
                        },
                        onPanEnd: (_){
                          setSettingsButtonPath('settings_button.png');
                        },
                        onTap: (){
                          Navigator.pushNamed(context, '/settings');
                          setSettingsButtonPath('settings_button.png');
                        },
                        child: Image.asset(settingsButtonPath)
                    )
                  ]
              )
            ]
        )
    );
  }
}
