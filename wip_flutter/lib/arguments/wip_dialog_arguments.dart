import 'package:flutter/material.dart';

class WIPDialogArguments {

  List<Widget> children;
  double dialogHeight;
  bool popUntilRoot;

  WIPDialogArguments({
    required this.children,
    required this.dialogHeight,
    required this.popUntilRoot
  });

}