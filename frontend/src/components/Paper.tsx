import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Chip from '@material-ui/core/Chip';
import Link from '@material-ui/core/Link';
import ListItem from '@material-ui/core/ListItem';
import ListItemAvatar from '@material-ui/core/ListItemAvatar';
import ListItemText from '@material-ui/core/ListItemText';
import Avatar from '@material-ui/core/Avatar';
import AssignmentIcon from '@material-ui/icons/Assignment';
import { blue, pink, grey } from '@material-ui/core/colors/';

const useStyles = makeStyles(theme => ({
  root: {
    width: '100%',
    maxWidth: 1080,
    backgroundColor: grey[100],
  },
}));

const getChip = (task, predicted) => {
  let color = pink[800];
  if (predicted != null && !Boolean(predicted)) {
    color = blue[800];
  }
  return (
    <Chip label={task} color="primary" style={{ backgroundColor: color }} />
  );
};

const getConfName = (conf, year) => {
  if (conf == null) {
    return null;
  } else if (year == null) {
    return conf;
  }
  return conf + ' ' + String(year);
};

const Paper = props => {
  const classes = useStyles();

  return (
    <div className="paper">
      <Link
        href={props.url ? props.url : null}
        color="inherit"
        underline="none"
      >
        <ListItem button className={classes.root}>
          <ListItemAvatar>
            <Avatar>
              <AssignmentIcon />
            </Avatar>
          </ListItemAvatar>
          <ListItemText
            primary={props.title}
            secondary={getConfName(props.conference, props.year)}
          />
          {getChip(props.task, props.predicted)}
        </ListItem>
      </Link>
    </div>
  );
};

export default Paper;
