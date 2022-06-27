import 'package:flutter/material.dart';

AlertDialog makeDialog(String title, String subtitle, List<String> otherText) {

  List<Widget> widgets = <Widget>[];

  for(String text in otherText) {
    widgets.add(Text(
      text,
        style: const TextStyle(
          color: Color.fromARGB(255, 2, 119, 189),
          fontFamily: 'PressStart2P',
          fontSize: 22,
        )
    ));
  }

  return AlertDialog(
      contentPadding: EdgeInsets.zero,
      content: Container(
        height: 400,
        decoration: const BoxDecoration(
          image: DecorationImage(
            image: AssetImage('assets/images/info_dialog.png'),
            fit: BoxFit.fill
          )
        ),
        child: Center(
          child: Wrap(
            direction: Axis.vertical,
            alignment: WrapAlignment.center,
            crossAxisAlignment: WrapCrossAlignment.center,
            spacing: 10,
            children: [],
          ),
        ),
      )
  );
}