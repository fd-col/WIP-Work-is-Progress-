import 'package:flutter/material.dart';
import 'package:wip_flutter/view/kingdom.dart';
import 'package:wip_flutter/view/settings.dart';
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
        '/start-story': (context) => const StartStory(title: 'Inizia una storia'),
        '/story-started': (context) => const StoryStarted(title: 'Storia iniziata'),
        '/kingdom': (context) => const Kingdom(title: 'Regno'),
        '/settings': (context) => const Settings(title: 'Impostazioni')
      },
      debugShowCheckedModeBanner: false,
    );
  }
}