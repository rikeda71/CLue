import { configure } from '@storybook/react';

configure(require.context('../src/components/stories', true, /\.js$/), module);
