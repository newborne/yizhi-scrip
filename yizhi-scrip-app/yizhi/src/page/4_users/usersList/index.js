import React, {Component} from 'react';
import {
  StatusBar,
  View,
  ScrollView,
  TouchableOpacity,
  Image,
  Text,
} from 'react-native';
import {BoxShadow} from 'react-native-shadow';
import {pxToDp} from '@src/util/pxToDp';
import GradientNavgation from '@src/component/GradientNavgation';
import SearchBox from '@src/component/SearchBox';
class Index extends Component {
  state = {
    txt: '',
  };
  render() {
    return (
      <ScrollView>
        <StatusBar backgroundColor="transparent" translucent={true} />
        <View style={{backgroundColor: '#f7f7f7'}}>
          <View style={{marginTop: pxToDp(10)}}>
            <SearchBox
              onChangeText={txt => this.setState({txt})}
              value={this.state.txt}
            />
          </View>
        </View>
      </ScrollView>
    );
  }
}

export default Index;
