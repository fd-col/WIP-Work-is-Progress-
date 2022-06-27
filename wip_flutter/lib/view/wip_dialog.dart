import 'package:flutter/material.dart';

List<Widget> makeChildrenSection(String title, String subtitle, List<String> otherText, bool isFirstSection) {

  List<Widget> children = <Widget>[];

  if(!isFirstSection) {
    children.add(const Text(
        '------',
        textAlign: TextAlign.center,
        style: TextStyle(
            color: Color.fromARGB(255, 2, 119, 189),
            fontFamily: 'PressStart2P',
            fontSize: 22
        ))
    );
  }

  children.add(Text(
      title,
      textAlign: TextAlign.center,
      style: const TextStyle(
          color: Color.fromARGB(255, 2, 119, 189),
          fontFamily: 'PressStart2P',
          fontSize: 22
      ))
  );

  children.add(Text(
      subtitle,
      textAlign: TextAlign.center,
      style: const TextStyle(
          color: Color.fromARGB(255, 2, 119, 189),
          fontFamily: 'PressStart2P',
          fontSize: 16
      ))
  );

  for(String text in otherText) {
    children.add(Text(
        text,
        textAlign: TextAlign.center,
        style: const TextStyle(
            color: Color.fromARGB(255, 2, 119, 189),
            fontFamily: 'PressStart2P',
            fontSize: 12
        )
    ));
  }

  return children;

}

AlertDialog makeDialog(List<Widget> children, BuildContext context) {

  children.add(
      GestureDetector(
          onTap: () {
            Navigator.pop(context);
          },
          child: Image.asset('assets/images/okay_button.png')
      )
  );

  return AlertDialog(
      contentPadding: EdgeInsets.zero,
      content: Container(
        height: 600,
        width: 300,
        decoration: const BoxDecoration(
          image: DecorationImage(
            image: AssetImage('assets/images/info_dialog.png'),
            fit: BoxFit.fill
          )
        ),
        child: Wrap(
          direction: Axis.vertical,
          alignment: WrapAlignment.center,
          runAlignment: WrapAlignment.center,
          crossAxisAlignment: WrapCrossAlignment.center,
          spacing: 10,
          children: children
        )
      )
  );
}