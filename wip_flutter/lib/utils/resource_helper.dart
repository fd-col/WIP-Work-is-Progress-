String fromShopElementAvatarToPath(String avatar) {

  String imagesPath = 'assets/images/avatar/';

  String avatarPath = '';

  switch(avatar) {

    case 'venere':
      avatarPath = 'venere.png';
      break;
    case 'magritte_apple':
      avatarPath = 'magritte.png';
      break;
    case 'girl_with_pearl_earring':
      avatarPath = 'ragazza_col_turbante.png';
      break;
    case 'the_scream':
      avatarPath = 'munch.png';
      break;
    case 'self_portrait':
      avatarPath = 'van_gogh_self_portrait.png';
      break;
    case 'david':
      avatarPath = 'david.png';
      break;
  }

  return imagesPath + avatarPath;

}

String fromShopElementBackgroundToPath(String background) {

  String imagesPath = 'assets/images/background/';

  String backgroundPath = '';

  switch(background) {

    case 'the_scream_background':
      backgroundPath = 'munch_bg_1.png';
      break;
    case 'the_persistence_of_memory':
      backgroundPath = 'dali_1.png';
      break;
    case 'hopper_nighthawks':
      backgroundPath = 'hopper_1.png';
      break;
    case 'creation_of_adam':
      backgroundPath = 'adam_1.png';
      break;
    case 'lovers':
      backgroundPath = 'magritte_kiss_1.png';
      break;
    case 'weathfield_with_crows':
      backgroundPath = 'field_with_crows_1.png';
      break;
  }

  return imagesPath + backgroundPath;

}

String fromShopElementEvolutedBackgroundToPath(String background, int backgroundEvolution) {

  String imagesPath = 'assets/images/background/';

  String evolutedBackgroundPath = '';

  switch(background) {

    case 'the_scream_background':
      switch(backgroundEvolution) {
        case 2:
          evolutedBackgroundPath = 'munch_bg_2.png';
          break;
        case 3:
          evolutedBackgroundPath = 'munch_bg_3.png';
          break;
        case 4:
          evolutedBackgroundPath = 'munch_bg_4.png';
          break;
      }
      break;
    case 'the_persistence_of_memory':
      switch(backgroundEvolution) {
        case 2:
          evolutedBackgroundPath = 'dali_2.png';
          break;
        case 3:
          evolutedBackgroundPath = 'dali_3.png';
          break;
        case 4:
          evolutedBackgroundPath = 'dali_4.png';
          break;
      }
      break;
    case 'hopper_nighthawks':
      switch(backgroundEvolution) {
        case 2:
          evolutedBackgroundPath = 'hopper_2.png';
          break;
        case 3:
          evolutedBackgroundPath = 'hopper_3.png';
          break;
        case 4:
          evolutedBackgroundPath = 'hopper_4.png';
          break;
      }
      break;
    case 'creation_of_adam':
      switch(backgroundEvolution) {
        case 2:
          evolutedBackgroundPath = 'adam_2.png';
          break;
        case 3:
          evolutedBackgroundPath = 'adam_3.png';
          break;
        case 4:
          evolutedBackgroundPath = 'adam_4.png';
          break;
      }
      break;
    case 'lovers':
      switch(backgroundEvolution) {
        case 2:
          evolutedBackgroundPath = 'magritte_kiss_2.png';
          break;
        case 3:
          evolutedBackgroundPath = 'magritte_kiss_3.png';
          break;
        case 4:
          evolutedBackgroundPath = 'magritte_kiss_4.png';
          break;
      }
      break;
    case 'weathfield_with_crows':
    switch(backgroundEvolution) {
      case 2:
        evolutedBackgroundPath = 'field_with_crows_2.png';
        break;
      case 3:
        evolutedBackgroundPath = 'field_with_crows_3.png';
        break;
      case 4:
        evolutedBackgroundPath = 'field_with_crows_4.png';
        break;
    }
    break;
  }

  return imagesPath + evolutedBackgroundPath;

}