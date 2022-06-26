import 'package:flutter/material.dart';
import 'view/story_started.dart';
import 'view/home.dart';
import 'view/start_story.dart';

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