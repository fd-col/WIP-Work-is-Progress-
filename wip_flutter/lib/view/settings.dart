import 'package:flutter/material.dart';

import 'menu.dart';

class Settings extends StatefulWidget {
  const Settings({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<Settings> createState() => _SettingsState();

}

class _SettingsState extends State<Settings> {

  String imagesPath = 'assets/images/home/';
  String menuImagesPath = 'assets/images/menu/';

  String playButtonPath = 'assets/images/home/play_button.png';
  String homeButtonPath = 'assets/images/menu/home_button.png';
  String kingdomButtonPath = 'assets/images/menu/kingdom_button.png';
  String settingsButtonPath = 'assets/images/menu/settings_button.png';

  late Image playButtonPressed;
  late Image homeButtonPressed;
  late Image kingdomButtonPressed;
  late Image settingsButtonPressed;

  @override
  void initState() {
    super.initState();
    playButtonPressed = Image.asset('${imagesPath}play_button_pressed.png');
    homeButtonPressed = Image.asset('${menuImagesPath}home_button_pressed.png');
    kingdomButtonPressed = Image.asset('${menuImagesPath}kingdom_button_pressed.png');
    settingsButtonPressed = Image.asset('${menuImagesPath}settings_button_pressed.png');
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    precacheImage(playButtonPressed.image, context);
    precacheImage(homeButtonPressed.image, context);
    precacheImage(kingdomButtonPressed.image, context);
    precacheImage(settingsButtonPressed.image, context);
  }

  void setPlayButtonPath(String playButtonPath) {
    setState(() {
      this.playButtonPath = imagesPath + playButtonPath;
    });
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
    return Scaffold(
      body: Container(
        decoration: const BoxDecoration(
            image: DecorationImage(
                image: AssetImage('assets/images/shared/background.png'),
                fit: BoxFit.fill
            )
        ),
        padding: const EdgeInsets.only(top: 340, bottom: 30),
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: const [
              SizedBox(
                height: 340,
                child: Text(
                    'Questa sezione\nnon è stata\nimplementata\nin Flutter',
                    textAlign: TextAlign.center,
                    style: TextStyle(
                      color: Colors.white,
                      fontFamily: 'PressStart2P',
                      fontSize: 22,
                    )
                )
              ),
              Menu()
            ]
          ),
        ),
      ),
    );
  }
}