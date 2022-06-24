import 'package:flutter/material.dart';

import 'Home.dart';
import 'StartStory.dart';
import 'StoryStarted.dart';

class WIP extends StatelessWidget {
  const WIP({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'WIP',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      initialRoute: '/',
      routes: {
        '/': (context) => const Home(title: 'Home'),
        '/start-story': (context) => const StartStory(title: 'Start Story'),
        '/story-started': (context) => const StoryStarted(title: 'Story Started')
      },
      debugShowCheckedModeBanner: false,
    );
  }
}