import 'package:flutter/material.dart';

class Kingdom extends StatefulWidget {
  const Kingdom({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<Kingdom> createState() => _KingdomState();

}

class _KingdomState extends State<Kingdom> {

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

            ],
          ),
        ),
      ),
    );
  }
}