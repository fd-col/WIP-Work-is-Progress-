import 'package:flutter/material.dart';
import 'package:wip_flutter/arguments/wip_dialog_arguments.dart';

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

class WIPDialog extends StatefulWidget {
  const WIPDialog({Key? key, required this.args}) : super(key: key);

  final WIPDialogArguments args;

  @override
  State<WIPDialog> createState() => _WIPDialogState();
}

class _WIPDialogState extends State<WIPDialog> {

  String dialogImagesPath = 'assets/images/dialog/';

  String okayButtonPath = 'assets/images/dialog/okay_button.png';

  late Image okayButtonPressed;

  @override
  void initState() {
    super.initState();
    okayButtonPressed =
        Image.asset('${dialogImagesPath}okay_button_pressed.png');
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    precacheImage(okayButtonPressed.image, context);
  }

  void setOkayButtonPath(String okayButtonPath) {
    setState(() {
      this.okayButtonPath = dialogImagesPath + okayButtonPath;
    });
  }

  @override
  Widget build(BuildContext context) {
    return AlertDialog(
        contentPadding: EdgeInsets.zero,
        content: Container(
            height: widget.args.dialogHeight,
            width: 300,
            decoration: const BoxDecoration(
                image: DecorationImage(
                    image: AssetImage('assets/images/dialog/info_dialog.png'),
                    fit: BoxFit.fill
                )
            ),
            child: Wrap(
              alignment: WrapAlignment.center,
              runAlignment: WrapAlignment.center,
              crossAxisAlignment: WrapCrossAlignment.center,
              direction: Axis.vertical,
              spacing: 10,
              children: [
                Wrap(
                    direction: Axis.vertical,
                    crossAxisAlignment: WrapCrossAlignment.center,
                    spacing: 10,
                    children: widget.args.children
                ),
                GestureDetector(
                    onTapDown: (_){
                      setOkayButtonPath('okay_button_pressed.png');
                    },
                    onPanDown: (_){
                      setOkayButtonPath('okay_button_pressed.png');
                    },
                    onTapCancel: (){
                      setOkayButtonPath('okay_button.png');
                    },
                    onPanEnd: (_){
                      setOkayButtonPath('okay_button.png');
                    },
                    onTap: () {
                      if(widget.args.popUntilRoot) {
                        Navigator.popUntil(context, ModalRoute.withName('/home'));
                      } else {
                        Navigator.pop(context);
                      }
                      setOkayButtonPath('okay_button.png');
                    },
                    child: Image.asset(okayButtonPath)
                )
              ],
            )
        )
    );
  }
}
