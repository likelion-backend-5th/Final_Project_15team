import * as React from "react";
import Box from "@mui/material/Box";
import Stack from "@mui/material/Stack";

import Feeds from "./feeds";

// const Item = styled(Paper)(({ theme }) => ({
//   backgroundColor: theme.palette.mode === "dark" ? "#1A2027" : "#fff",
//   ...theme.typography.body2,
//   padding: theme.spacing(1),
//   textAlign: "center",
//   color: theme.palette.text.secondary,
// }));

export default function Feedswrap() {
  return (
    <Box
      sx={{
        width: "50%",
        margin: "auto",
      }}>
      <Stack spacing={2}>
        {[1, 2, 3].map(function (i, b) {
          return <Feeds></Feeds>;
        })}
      </Stack>
    </Box>
  );
}
