import React from "react";
import MDSpinner from "react-md-spinner";

type LoadingProps = {
  isShown: boolean;
};

const loadingStyle = {
  margin: "100px auto",
  display: "block",
};

const Loading: React.FC<LoadingProps> = props => {
  if (!props.isShown) {
    return null;
  }
  return (
    <React.Fragment>
      <MDSpinner style={loadingStyle} size={100} />
    </React.Fragment>
  );
};

export default Loading;
