import 'dart:async';

import 'package:flutter/material.dart';

class SplashPage extends StatelessWidget {
  const SplashPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {

    Timer(
      const Duration(seconds: 1),
        () => Navigator.pushReplacementNamed(context, '/home')
    );

    return Scaffold(
      body: Container(
        decoration: const BoxDecoration(
            image: DecorationImage(
                image: AssetImage('assets/images/shared/background.png'),
                fit: BoxFit.fill
            )
        ),
        child: Center(
          child: Wrap(
              direction: Axis.vertical,
              crossAxisAlignment: WrapCrossAlignment.center,
              spacing: 10,
              children: [
                SizedBox(
                    height: 200,
                    width: 200,
                    child: FittedBox(
                      fit: BoxFit.cover,
                      child: Image.asset('assets/images/shared/story_stand.png'),
                    )
                ),
                SizedBox(
                    height: 35,
                    width: 280,
                    child: FittedBox(
                      fit: BoxFit.cover,
                      child: Image.asset('assets/images/splash-page/quote.png'),
                    )
                )
              ]
          ),
        ),
      ),
    );
  }
}