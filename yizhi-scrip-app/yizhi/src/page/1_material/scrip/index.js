import React, {Component} from 'react';
import {View} from 'react-native';
import GradientNavgation from '@src/component/GradientNavgation';

class Index extends Component {
  render() {
    return (
      <View>
        <GradientNavgation title={'易知纸条'} />
      </View>
    );
  }
}
export default Index;
