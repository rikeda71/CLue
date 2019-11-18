import React from 'react';
import { makeStyles, withStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Chip from '@material-ui/core/Chip';
import Dialog from '@material-ui/core/Dialog';
import Typography from '@material-ui/core/Typography';
import MuiDialogTitle from '@material-ui/core/DialogTitle';
import MuiDialogContent from '@material-ui/core/DialogContent';
import MuiDialogActions from '@material-ui/core/DialogActions';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
import { blue, pink, grey } from '@material-ui/core/colors/';
import { Description, SaveAlt } from '@material-ui/icons';

const dialogStyles = theme => ({
  root: {
    margin: 0,
    padding: theme.spacing(2),
  },
  closeButton: {
    position: 'absolute',
    right: theme.spacing(1),
    top: theme.spacing(1),
    color: theme.palette.grey[500],
  },
});

const cardStyles = makeStyles({
  card: {
    maxWidth: 1080,
    backgroundColor: grey[100],
  },
});

const DialogTitle = withStyles(dialogStyles)(props => {
  const { children, classes, onClose } = props;
  return (
    <MuiDialogTitle disableTypography className={classes.root}>
      <Typography variant="h6">{children}</Typography>
      {onClose ? (
        <IconButton
          aria-label="close"
          className={classes.closeButton}
          onClick={onClose}
        >
          <CloseIcon />
        </IconButton>
      ) : null}
    </MuiDialogTitle>
  );
});

const DialogContent = withStyles(theme => ({
  root: {
    padding: theme.spacing(2),
  },
}))(MuiDialogContent);

const getChip = (task, predicted) => {
  if (task == null) {
    return;
  }
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

const PaperContent = props => {
  return (
    <div className="paper-content">
      <CardContent>
        <Typography gutterBottom variant="h5" component="h2">
          {props.title}
        </Typography>
        <Typography variant="body2" color="textSecondary" component="p">
          {getConfName(props.conf, props.year)}
        </Typography>
      </CardContent>
    </div>
  );
};

export default function CardPaper(props) {
  const classes = cardStyles();
  const [open, setOpen] = React.useState(false);

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  return (
    <Card className={classes.card}>
      <CardActionArea>
        <PaperContent
          title={props.title}
          conf={props.conference}
          year={props.year}
        />
      </CardActionArea>
      <CardActions>
        <Button
          size="small"
          color="default"
          onClick={handleClickOpen}
          onClose={handleClose}
        >
          <Description />
          {props.language == 'japanese' ? 'Introduction' : 'Abstruct'}
        </Button>
        <Dialog
          onClose={handleClose}
          aria-labelledby="dialog"
          open={open}
          fullWidth="lg"
          maxWidth={true}
        >
          <DialogTitle id="dialog" onClose={handleClose}>
            {props.title}
          </DialogTitle>
          <DialogContent dividers>
            <Typography gutterBottom>{props.abstruct}</Typography>
          </DialogContent>
        </Dialog>
        <Button
          size="small"
          color="default"
          href={props.url ? props.url : null}
        >
          <SaveAlt />
          Download
        </Button>
        <Button size="small" color="primary">
          {getChip(props.task, props.predicted)}
        </Button>
      </CardActions>
    </Card>
  );
}
